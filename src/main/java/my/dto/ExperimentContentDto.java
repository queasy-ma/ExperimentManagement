package my.dto;

public class ExperimentContentDto {
    private Long id;
    private String details;
    private Long experimentId; // 对应Experiment的id

    public ExperimentContentDto(long id, String details, long experimentId) {
        this.id = id;
        this.details = details;
        this.experimentId = experimentId;
    }

    public Long getExperimentId() {
        return experimentId;
    }

    public String getDetails() {
        return details;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId(){return id;}

    // 构造函数、getter和setter
}
