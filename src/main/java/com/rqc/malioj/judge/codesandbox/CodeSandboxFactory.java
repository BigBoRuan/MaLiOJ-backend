package com.rqc.malioj.judge.codesandbox;

import com.rqc.malioj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.rqc.malioj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.rqc.malioj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @author 阮其昌
 * @description: 代码沙箱创建工厂 根据字符串参数创建对应的代码沙箱实现
 * @date 2025/7/20 10:18
 */
public class CodeSandboxFactory {

    /**
     * 创建代码沙箱示例
     *
     * @param type
     * @return
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
