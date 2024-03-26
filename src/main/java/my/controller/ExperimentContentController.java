package my.controller;

import my.dto.ExperimentContentDto;
import my.service.ExperimentContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experiment-contents")
public class ExperimentContentController {

    private final ExperimentContentService contentService;

    @Autowired
    public ExperimentContentController(ExperimentContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/add")
    public ResponseEntity<ExperimentContentDto> createExperimentContent(@RequestBody ExperimentContentDto dto) {
        ExperimentContentDto savedDto = contentService.addExperimentContent(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExperimentContent(@PathVariable Long id) {
        contentService.deleteExperimentContent(id);
        return ResponseEntity.ok().build();
    }
}
