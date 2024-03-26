package my.service;

import jakarta.persistence.EntityNotFoundException;
import my.dto.ExperimentContentDto;
import my.model.Experiment;
import my.model.ExperimentContent;
import my.repository.ExperimentContentRepository;
import my.repository.ExperimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperimentContentService {

    private final ExperimentContentRepository contentRepository;
    private final ExperimentRepository experimentRepository;

    @Autowired
    public ExperimentContentService(ExperimentContentRepository contentRepository, ExperimentRepository experimentRepository) {
        this.contentRepository = contentRepository;
        this.experimentRepository = experimentRepository;
    }

    public ExperimentContentDto addExperimentContent(ExperimentContentDto dto) {
        Experiment experiment = experimentRepository.findById(dto.getExperimentId())
                .orElseThrow(() -> new EntityNotFoundException("Experiment not found with id: " + dto.getExperimentId()));

        ExperimentContent content = new ExperimentContent();
        content.setDetails(dto.getDetails());
        content.setExperiment(experiment);

        content = contentRepository.save(content);

        dto.setId(content.getId());
        return dto;
    }

    public void deleteExperimentContent(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new EntityNotFoundException("ExperimentContent not found with id: " + id);
        }
        contentRepository.deleteById(id);
    }
}

