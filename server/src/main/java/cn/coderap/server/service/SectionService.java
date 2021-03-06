package cn.coderap.server.service;

import cn.coderap.server.dto.SectionDto;
import cn.coderap.server.dto.SectionPageDto;
import cn.coderap.server.mapper.SectionMapper;
import cn.coderap.server.pojo.Section;
import cn.coderap.server.pojo.SectionExample;
import cn.coderap.server.util.CopyUtil;
import cn.coderap.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;
    @Resource
    private CourseService courseService;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(SectionPageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        SectionExample.Criteria criteria = sectionExample.createCriteria();
        if (!StringUtils.isEmpty(pageDto.getCourseId())) {
            criteria.andCourseIdEqualTo(pageDto.getCourseId());
        }
        if (!StringUtils.isEmpty(pageDto.getChapterId())) {
            criteria.andChapterIdEqualTo(pageDto.getChapterId());
        }
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);

        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtoList = CopyUtil.copyList(sectionList, SectionDto.class);
        pageDto.setList(sectionDtoList);
    }

    /**
     * 保存，id没有值时新增，有值时更新
     * @param sectionDto
     */
    @Transactional
    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        //一次操作（新增小节或修改小节时长）会更新或修改多张表（section和course），一般为了保证数据一致，需要增加事务处理
        if (StringUtils.isEmpty(section.getId())) {
            insert(section);
        } else {
            update(section);
        }
        //更新课程总时长
        courseService.updateTime(sectionDto.getCourseId());
    }

    /**
     * 新增
     * @param section
     */
    private void insert(Section section) {
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    /**
     * 更新
     * @param section
     */
    private void update(Section section) {
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}
