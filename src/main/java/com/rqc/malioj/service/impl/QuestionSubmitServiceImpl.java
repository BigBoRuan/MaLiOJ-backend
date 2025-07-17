package com.rqc.malioj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rqc.malioj.common.ErrorCode;
import com.rqc.malioj.exception.BusinessException;
import com.rqc.malioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.rqc.malioj.model.entity.Question;
import com.rqc.malioj.model.entity.QuestionSubmit;
import com.rqc.malioj.model.entity.QuestionSubmit;
import com.rqc.malioj.model.entity.User;
import com.rqc.malioj.model.enums.QuestionSubmitLanguageEnum;
import com.rqc.malioj.model.enums.QuestionSubmitStatusEnum;
import com.rqc.malioj.service.QuestionService;
import com.rqc.malioj.service.QuestionSubmitService;
import com.rqc.malioj.service.QuestionSubmitService;
import com.rqc.malioj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author 阮其昌
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2025-07-17 16:14:38
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{

    @Resource
    private QuestionService questionService;

    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        // 校验编程语言是否报错
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum enumByValue = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (enumByValue == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        long questionId = questionSubmitAddRequest.getQuestionId();

        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());

        questionSubmit.setLanguage(questionSubmitAddRequest.getLanguage());
        // 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }


    return questionSubmit.getId();
    }
}




