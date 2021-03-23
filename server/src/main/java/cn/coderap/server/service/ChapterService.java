package cn.coderap.server.service;

import cn.coderap.server.dto.ChapterDto;
import cn.coderap.server.mapper.ChapterMapper;
import cn.coderap.server.pojo.Chapter;
import cn.coderap.server.pojo.ChapterExample;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yw
 * 2021/3/11
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);

        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            ChapterDto chapterDto = new ChapterDto();
            Chapter chapter = chapterList.get(i);
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
