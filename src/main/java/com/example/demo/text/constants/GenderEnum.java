package com.example.demo.text.constants;

import lombok.Getter;

/**
 * @author HouJianJun
 * @description:
 * @date 2022/6/16 18:41
 */
@Getter
public enum GenderEnum {

    MALE(10, "男"),
    FEMALE(11, "女"),
    ;

    private final Integer code;
    private final String msg;

    GenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

