package com.example.demo.text.controller;

import com.example.demo.text.constants.GenderEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HouJianJun
 * @description:
 * @date 2022/6/16 18:42
 */
@RestController
@RequestMapping("activityInfo")
public class TestController {

    @PostMapping("gender_enum")
    public GenderEnum enumFromParam(@RequestParam GenderEnum gender) {
        return gender;
    }

}
