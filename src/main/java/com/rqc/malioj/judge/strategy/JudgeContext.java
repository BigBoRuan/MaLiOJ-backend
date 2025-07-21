package com.rqc.malioj.judge.strategy;

import com.rqc.malioj.model.dto.question.JudgeCase;
import com.rqc.malioj.model.dto.questionsubmit.JudgeInfo;
import com.rqc.malioj.model.entity.Question;
import com.rqc.malioj.model.entity.QuestionSubmit;
import com.rqc.malioj.model.vo.QuestionVO;
import lombok.Data;

import java.util.List;

/**
 * @author 阮其昌
 * @description: 上下文策略 用于定义策略中传递的参数
 * @date 2025/7/21 8:56
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
