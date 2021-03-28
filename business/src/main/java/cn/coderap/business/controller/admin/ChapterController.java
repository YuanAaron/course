package cn.coderap.business.controller.admin;

import cn.coderap.server.dto.ChapterDto;
import cn.coderap.server.dto.PageDto;
import cn.coderap.server.dto.ResponseDto;
import cn.coderap.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yw
 * 2021/3/11
 */
@RestController
// /admin用于后台的接口，/web用于网站的接口。
// 接口设计中，用不同的请求前缀代表不同的入口，做接口隔离，方便做鉴权、统计、监控等
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    /**
     * @RequestBody存在的必要性：
     * 前端：post请求有多种参数传递方式，可以通过header里的Content-Type来标识，常见的有两种，一种是表单的方式（jquery默认），
     *      另一种是json(流)的方式（Vue和Angular默认）。
     * 后端：后端对上卖弄两种参数传递方式的接收方式是不一样的，对表单的接收不需要使用@RequestBody，但是对流的接收需要使用@RequestBody
     * @param pageDto
     * @return
     */
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOGGER.info("pageDto: {}",pageDto); //日志输出使用占位符{}
        ResponseDto responseDto = new ResponseDto();
        chapterService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 增加和修改功能弹出来的模态框是同一个，这两个功能调用的vue、controller、service中的save方法都是同一个，
     * 只是到service层再根据id是否有值来判断是新增还是修改（id没有值，即为新增；否则，为修改）。
     * @param chapterDto
     * @return
     */
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        LOGGER.info("chapterDto: {}",chapterDto);
        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }
}
