package my.service;

import jakarta.persistence.EntityNotFoundException;
import my.dto.ExperimentDto;
import my.model.Course;
import my.model.Experiment;
import my.repository.CourseRepository;
import my.repository.ExperimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperimentService {

    private final ExperimentRepository experimentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ExperimentService(ExperimentRepository experimentRepository, CourseRepository courseRepository) {
        this.experimentRepository = experimentRepository;
        this.courseRepository = courseRepository;
    }

    public ExperimentDto addExperiment(ExperimentDto experimentDto) {
        Course course = courseRepository.findById(experimentDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + experimentDto.getCourseId()));
        Experiment experiment = new Experiment();
        experiment.setTitle(experimentDto.getTitle());
        experiment.setType(experimentDto.getType());
        experiment.setCourse(course);
        experiment = experimentRepository.save(experiment);

        experimentDto.setId(experiment.getId());
        return experimentDto;
    }

    public void deleteExperiment(Long id) {
        if (!experimentRepository.existsById(id)) {
            throw new EntityNotFoundException("Experiment not found with id: " + id);
        }
        experimentRepository.deleteById(id);
    }
}