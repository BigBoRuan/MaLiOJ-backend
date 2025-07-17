package com.rqc.malioj.service;

import com.rqc.malioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.rqc.malioj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rqc.malioj.model.entity.User;

/**
* @author 阮其昌
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2025-07-17 16:14:38
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


}
