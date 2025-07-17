package com.rqc.malioj.model.dto.questionsubmit;

import lombok.Data;

/**
 * @author 阮其昌
 * @description: 判题信息
 * @date 2025/7/17 16:34
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗的内存
     */
    private long memory;

    /**
     * 消耗的时间
     */
    private long time;
}
