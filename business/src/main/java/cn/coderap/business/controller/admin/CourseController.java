package cn.coderap.business.controller.admin;

import cn.coderap.server.dto.CourseDto;
import cn.coderap.server.dto.PageDto;
import cn.coderap.server.dto.ResponseDto;
import cn.coderap.server.service.CourseService;
import cn.coderap.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程表";

    @Resource
    private CourseService courseService;

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id没有值时新增，有值时更新
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        //校验
        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }
}
