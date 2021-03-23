package cn.coderap.server.service;

import cn.coderap.server.dto.ChapterDto;
import cn.coderap.server.mapper.ChapterMapper;
import cn.coderap.server.pojo.Chapter;
import cn.coderap.server.pojo.ChapterExample;
import com.github.pagehelper.PageHelper;
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
        //pageNum从1开始
        //pagehelper插件分页规则：调用startPage方法后，执行的第一个select语句会进行分页
        //实质：
        //sql1: select count(0) from chapter，查询总条数
        //sql2: select * from chapter limit 0,1，为select语句增加limit m,n，其中行号m是从0开始的，n表示查几条
        PageHelper.startPage(1, 1);
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
