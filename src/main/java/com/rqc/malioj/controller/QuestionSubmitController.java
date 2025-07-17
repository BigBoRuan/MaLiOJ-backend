package com.rqc.malioj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rqc.malioj.annotation.AuthCheck;
import com.rqc.malioj.common.BaseResponse;
import com.rqc.malioj.common.ErrorCode;
import com.rqc.malioj.common.ResultUtils;
import com.rqc.malioj.constant.UserConstant;
import com.rqc.malioj.exception.BusinessException;
import com.rqc.malioj.model.dto.question.QuestionQueryRequest;
import com.rqc.malioj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.rqc.malioj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.rqc.malioj.model.entity.Question;
import com.rqc.malioj.model.entity.QuestionSubmit;
import com.rqc.malioj.model.entity.User;
import com.rqc.malioj.model.vo.QuestionSubmitVO;
import com.rqc.malioj.service.QuestionSubmitService;
import com.rqc.malioj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return  提交记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionId = questionSubmitAddRequest.getQuestionId();
        Long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表 除了管理员之外，普通用户只能看到答案 提交代码等公开信息
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 得到原始数据
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
    }

}
