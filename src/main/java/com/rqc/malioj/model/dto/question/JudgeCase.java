package com.rqc.malioj.model.dto.question;

import lombok.Data;

/**
 * @author 阮其昌
 * @description: 题目用例
 * @date 2025/7/17 16:34
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
