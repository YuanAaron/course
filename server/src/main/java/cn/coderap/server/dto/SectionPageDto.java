package cn.coderap.server.dto;

/**
 * Created by yw
 * 2021/4/13
 */
public class SectionPageDto extends PageDto {
    private String courseId;
    private String chapterId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }
}
