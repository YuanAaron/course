package cn.coderap.server.service;

import cn.coderap.server.mapper.HelloMapper;
import cn.coderap.server.pojo.Hello;
import cn.coderap.server.pojo.HelloExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yw
 * 2021/3/11
 */
@Service
public class HelloService {

    @Resource
    private HelloMapper helloMapper;

//    public List<Hello> list() {
//        return helloMapper.selectByExample(null);
//    }

//    public List<Hello> list() {
//        HelloExample helloExample = new HelloExample();
//        helloExample.setOrderByClause("id desc");
//        return helloMapper.selectByExample(helloExample);
//    }

    public List<Hello> list() {
        HelloExample helloExample = new HelloExample();
        HelloExample.Criteria criteria = helloExample.createCriteria();
        criteria.andIdEqualTo(2);
        helloExample.setOrderByClause("id desc");
        return helloMapper.selectByExample(helloExample);
    }
}
