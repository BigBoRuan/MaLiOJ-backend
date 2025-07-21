package com.rqc.malioj.judge.codesandbox;

import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 阮其昌
 * @description:
 * @date 2025/7/20 10:31
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
