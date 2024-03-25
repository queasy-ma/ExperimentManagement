package my.controller;

import my.dto.ExperimentDto;
import my.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experiments")
public class ExperimentController {

    private final ExperimentService experimentService;

    @Autowired
    public ExperimentController(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @PostMapping("/add")
    public ResponseEntity<ExperimentDto> createExperiment(@RequestBody ExperimentDto experimentDto) {
        ExperimentDto savedExperiment = experimentService.addExperiment(experimentDto);
        return new ResponseEntity<>(savedExperiment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExperiment(@PathVariable Long id) {
        experimentService.deleteExperiment(id);
        return ResponseEntity.ok().build();
    }
}
