package cn.coderap.server.dto;

/**
 * DTO（Data Transfer Object，数据传输对象）：用于数据传输
 * DTO与pojo的区别：
 * 1、pojo内的实体是mybatis generator自动生成的，不允许手动修改，一旦再次生成实体类，所做的修改会被覆盖；
 * 2、pojo一般用于service和mapper，dto一般用于controller和service。
 */
public class ChapterDto {
    private String id;

    private String courseId;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}