package com.mxt.mockdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Simple
 * @Create 2022/4/14 10:15
 */
@RestController
public class TestMockController {

    @GetMapping("/test/mockmvc")
    public String testMockMvc(@RequestParam(name = "name") String name) {
        return "MockMvc , hello " + name;
    }
}
