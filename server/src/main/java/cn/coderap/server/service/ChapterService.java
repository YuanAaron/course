package cn.coderap.server.service;

import cn.coderap.server.dto.ChapterDto;
import cn.coderap.server.dto.PageDto;
import cn.coderap.server.mapper.ChapterMapper;
import cn.coderap.server.pojo.Chapter;
import cn.coderap.server.pojo.ChapterExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public void list(PageDto pageDto) {
        //pageNum从1开始
        //pagehelper插件分页规则：调用startPage方法后，执行的第一个select语句会进行分页
        //实质：
        //sql1: select count(0) from chapter，查询总条数
        //sql2: select * from chapter limit 0,1，为select语句增加limit m,n，其中行号m是从0开始的，n表示查几条
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample); //如果前端不传参数(pageDto.page为0，pageDto.size也为0)，默认查询所有数据

        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            ChapterDto chapterDto = new ChapterDto();
            Chapter chapter = chapterList.get(i);
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }

        pageDto.setList(chapterDtoList);
    }
}
