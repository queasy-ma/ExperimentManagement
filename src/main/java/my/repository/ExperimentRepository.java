package my.repository;

import my.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperimentRepository extends JpaRepository<Experiment, Long> {
    List<Experiment> findByCourse_Id(Long courseId);
}

