package my.controller;

import my.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    private final ExportService exportService;

    @Autowired
    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<byte[]> exportData(@PathVariable String type,
                                             @RequestParam(required = false, defaultValue = "") List<Long> courseIds,
                                             @RequestParam(required = false, defaultValue = "") List<Long> experimentIds) throws Exception {
        byte[] data = exportService.exportData(courseIds, experimentIds, type);
        String mimeType;
        String fileExtension;

        if ("excel".equalsIgnoreCase(type)) {
            mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            fileExtension = ".xlsx";
        } else if ("pdf".equalsIgnoreCase(type)) {
            mimeType = "application/pdf";
            fileExtension = ".pdf";
        } else {
            throw new IllegalArgumentException("Unsupported export type: " + type);
        }

        String filename = "export" + fileExtension;
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(data);
    }
}

