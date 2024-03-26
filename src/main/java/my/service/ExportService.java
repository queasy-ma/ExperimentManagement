package my.service;

import java.util.List;

public interface ExportService {
    byte[] exportData(List<Long> courseIds, List<Long> experimentIds, String type) throws Exception;
}

