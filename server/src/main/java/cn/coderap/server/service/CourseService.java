package cn.coderap.server.service;

import cn.coderap.server.dto.CourseDto;
import cn.coderap.server.dto.PageDto;
import cn.coderap.server.mapper.CourseMapper;
import cn.coderap.server.mapper.my.MyCourseMapper;
import cn.coderap.server.pojo.Course;
import cn.coderap.server.pojo.CourseExample;
import cn.coderap.server.util.CopyUtil;
import cn.coderap.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private MyCourseMapper mycourseMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        CourseExample.Criteria criteria = courseExample.createCriteria();
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
        pageDto.setList(courseDtoList);
    }

    /**
     * 保存，id没有值时新增，有值时更新
     * @param courseDto
     */
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(course.getId())) {
            insert(course);
        } else {
            update(course);
        }
    }

    /**
     * 新增
     * @param course
     */
    private void insert(Course course) {
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
     * 更新
     * @param course
     */
    private void update(Course course) {
        course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新课程时长
     * @param courseId
     */
    public void updateTime(String courseId) {
        LOGGER.info("更新课程时长：{}",courseId);
        mycourseMapper.updateTime(courseId);
    }
}
