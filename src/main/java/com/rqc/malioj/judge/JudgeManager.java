package com.rqc.malioj.judge;

import com.rqc.malioj.judge.strategy.DefaultJudgeStrategy;
import com.rqc.malioj.judge.strategy.JavaLanguageJudgeStrategy;
import com.rqc.malioj.judge.strategy.JudgeContext;
import com.rqc.malioj.judge.strategy.JudgeStrategy;
import com.rqc.malioj.model.dto.questionsubmit.JudgeInfo;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author 阮其昌
 * @description: 目的 尽量简化对判题功能的调用 统一入口
 * @date 2025/7/21 9:21
 */
@Data
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext){
        String language = judgeContext.getQuestionSubmit().getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
