package com.rqc.malioj.judge.codesandbox.impl;

import com.rqc.malioj.judge.codesandbox.CodeSandbox;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author 阮其昌
 * @description: 远程代码沙箱 实际调用接口的沙箱
 * @date 2025/7/20 9:57
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
