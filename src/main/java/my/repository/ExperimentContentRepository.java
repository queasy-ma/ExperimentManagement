package my.repository;

import my.model.ExperimentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentContentRepository extends JpaRepository<ExperimentContent, Long> {
    List<ExperimentContent> findByExperiment_Id(Long experimentId);
}

