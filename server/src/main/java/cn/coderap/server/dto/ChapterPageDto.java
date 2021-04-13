package cn.coderap.server.dto;

/**
 * Created by yw
 * 2021/4/13
 */
public class ChapterPageDto extends PageDto{

    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
