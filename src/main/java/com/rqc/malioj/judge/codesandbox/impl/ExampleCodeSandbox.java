package com.rqc.malioj.judge.codesandbox.impl;

import com.rqc.malioj.judge.codesandbox.CodeSandbox;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeResponse;
import com.rqc.malioj.model.dto.questionsubmit.JudgeInfo;
import com.rqc.malioj.model.enums.JudgeInfoMessageEnum;
import com.rqc.malioj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author 阮其昌
 * @description: 示例代码沙箱 仅为了跑通业务流程
 * @date 2025/7/20 9:57
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(String.valueOf(QuestionSubmitStatusEnum.SUCCESS.getValue()));
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);

        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;

    }
}
