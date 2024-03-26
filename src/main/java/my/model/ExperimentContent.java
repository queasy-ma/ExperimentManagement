package my.model;

import jakarta.persistence.*;

@Entity
@Table(name = "experiment_content")
public class ExperimentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_id")
    private Experiment experiment;

    public void setDetails(String details) {
        this.details = details;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public Long getExperimentId() {
        return experiment.getId();
    }

    // 构造函数、getter和setter
}
