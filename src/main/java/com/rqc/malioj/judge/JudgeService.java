package com.rqc.malioj.judge;

import com.rqc.malioj.model.entity.QuestionSubmit;
import com.rqc.malioj.model.vo.QuestionSubmitVO;

/**
 * @author 阮其昌
 * @description:
 * @date 2025/7/20 11:24
 */
public interface JudgeService {

    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(Long questionSubmitId);
}
