package my.dto;
import java.util.ArrayList;
import java.util.List;
public class ExperimentDto {
    private Long id;
    private String title;
    private String type;
    private Long courseId; // 用于传递course的id

    private List<ExperimentContentDto> experimentContents = new ArrayList<>();

    public void addExperimentContent(ExperimentContentDto experimentContent) {
        this.experimentContents.add(experimentContent);
    }

    public ExperimentDto(Long id, String title, String type, long courseId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.courseId = courseId;
    }

    public Long getId() { return id; }
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
    public List<ExperimentContentDto> getExperimentContents() { return experimentContents; }

    public void setExperimentContents(List<ExperimentContentDto> orDefault) {
        this.experimentContents = orDefault;
    }

    // 省略构造函数、getter和setter方法
}

