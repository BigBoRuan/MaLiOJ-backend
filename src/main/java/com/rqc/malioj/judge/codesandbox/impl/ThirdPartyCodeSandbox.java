package com.rqc.malioj.judge.codesandbox.impl;

import com.rqc.malioj.judge.codesandbox.CodeSandbox;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author 阮其昌
 * @description: 第三方代码沙箱
 * @date 2025/7/20 9:57
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
