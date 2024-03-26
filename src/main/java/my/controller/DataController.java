package my.controller;

import my.dto.AllDataDto;
import my.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/all")
    public ResponseEntity<AllDataDto> getAllData() {
        AllDataDto allData = dataService.getAllData();
        return ResponseEntity.ok(allData);
    }
}

