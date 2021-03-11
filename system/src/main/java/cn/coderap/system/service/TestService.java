package cn.coderap.system.service;

import cn.coderap.system.mapper.TestMapper;
import cn.coderap.system.pojo.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yw
 * 2021/3/11
 */
@Service
public class TestService {

    @Resource //TODO @Resource和@Autowired有啥区别？
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
