package cn.coderap.business.controller.admin;

import cn.coderap.server.dto.ChapterDto;
import cn.coderap.server.pojo.Chapter;
import cn.coderap.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yw
 * 2021/3/11
 */
@RestController
// /admin用于后台的接口，/web用于网站的接口。
// 接口设计中，用不同的请求前缀代表不同的入口，做接口隔离，方便做鉴权、统计、监控等
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public List<ChapterDto> chapter() {
        return chapterService.list();
    }
}
