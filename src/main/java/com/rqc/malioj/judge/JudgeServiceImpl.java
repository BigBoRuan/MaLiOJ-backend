package com.rqc.malioj.judge;

import cn.hutool.json.JSONUtil;
import com.rqc.malioj.common.ErrorCode;
import com.rqc.malioj.exception.BusinessException;
import com.rqc.malioj.judge.codesandbox.CodeSandbox;
import com.rqc.malioj.judge.codesandbox.CodeSandboxFactory;
import com.rqc.malioj.judge.codesandbox.CodeSandboxProxy;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.rqc.malioj.judge.strategy.DefaultJudgeStrategy;
import com.rqc.malioj.judge.strategy.JudgeContext;
import com.rqc.malioj.judge.strategy.JudgeStrategy;
import com.rqc.malioj.model.dto.question.JudgeCase;
import com.rqc.malioj.model.dto.question.JudgeConfig;
import com.rqc.malioj.model.dto.questionsubmit.JudgeInfo;
import com.rqc.malioj.model.entity.Question;
import com.rqc.malioj.model.entity.QuestionSubmit;
import com.rqc.malioj.model.enums.JudgeInfoMessageEnum;
import com.rqc.malioj.model.enums.QuestionSubmitLanguageEnum;
import com.rqc.malioj.model.enums.QuestionSubmitStatusEnum;
import com.rqc.malioj.model.vo.QuestionSubmitVO;
import com.rqc.malioj.service.QuestionService;
import com.rqc.malioj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 阮其昌
 * @description:
 * @date 2025/7/20 11:40
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private JudgeManager judgeManager;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        // 1. 传入题目id 获取到对应的题目
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }

        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 不为等待中
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }

        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目更新状态错误");
        }

        // 调用代码沙箱
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> list = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = list.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);

        List<String> outputList = executeCodeResponse.getOutputList();


        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(list);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);



        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        // 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目更新状态错误");
        }
        QuestionSubmit result = questionSubmitService.getById(questionId);

        return result;
    }
}
