package cn.coderap.system.controller;

import cn.coderap.server.pojo.Hello;
import cn.coderap.server.pojo.Test;
import cn.coderap.server.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yw
 * 2021/3/11
 */
@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @RequestMapping("/hello")
    public List<Hello> hello() {
        return helloService.list();
    }
}
