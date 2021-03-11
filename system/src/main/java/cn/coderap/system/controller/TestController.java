package cn.coderap.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yw
 * 2021/3/11
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "hello spring!";
    }
}
