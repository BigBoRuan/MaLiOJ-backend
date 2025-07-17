package com.rqc.malioj.model.dto.question;

import lombok.Data;

/**
 * @author 阮其昌
 * @description: 题目配置
 * @date 2025/7/17 16:34
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制 ms
     */
    private long timeLimit;

    /**
     * 内存限制 kb
     */
    private long memoryLimit;

    /**
     * 堆栈限制 kb
     */
    private long stackLimit;
}
