package my.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {
    private Long id;
    private String name;
    private String description;

    private List<ExperimentDto> experiments = new ArrayList<>();
    public void addExperiment(ExperimentDto experiment) {
        this.experiments.add(experiment);
    }

    // Default constructor, getters and setters
    public CourseDto() {
    }

    public CourseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }



    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public List<ExperimentDto> getExperiments() { return experiments; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExperiments(List<ExperimentDto> orDefault) {
        this.experiments = orDefault;
    }
}
