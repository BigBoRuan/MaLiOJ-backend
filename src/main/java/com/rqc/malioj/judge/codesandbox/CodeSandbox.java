package com.rqc.malioj.judge.codesandbox;

import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeRequest;
import com.rqc.malioj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author 阮其昌
 * @description: 代码沙箱的接口
 * @date 2025/7/20 9:48
 */
public interface CodeSandbox {
    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
