package my.dto;

public class ExperimentDto {
    private Long id;
    private String title;
    private String type;
    private Long courseId; // 用于传递course的id

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    // 省略构造函数、getter和setter方法
}

