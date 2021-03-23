package cn.coderap.server.service;

import cn.coderap.server.mapper.ChapterMapper;
import cn.coderap.server.pojo.Chapter;
import cn.coderap.server.pojo.ChapterExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yw
 * 2021/3/11
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list() {
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        criteria.andIdEqualTo("1");
        chapterExample.setOrderByClause("id desc");
        return chapterMapper.selectByExample(chapterExample);
    }
}
