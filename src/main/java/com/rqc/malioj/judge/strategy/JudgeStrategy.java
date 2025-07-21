package com.rqc.malioj.judge.strategy;

import com.rqc.malioj.model.dto.questionsubmit.JudgeInfo;

/**
 * @author 阮其昌
 * @description: 判题策略
 * @date 2025/7/21 8:56
 */
public interface JudgeStrategy {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
