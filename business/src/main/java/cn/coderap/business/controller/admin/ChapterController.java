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
@RequestMapping("/admin")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<ChapterDto> chapter() {
        return chapterService.list();
    }
}
