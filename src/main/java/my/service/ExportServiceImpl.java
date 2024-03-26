package my.service;

import com.itextpdf.layout.Document;
import my.model.Course;
import my.model.Experiment;
import my.repository.CourseRepository;
import my.repository.ExperimentRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



@Service
public class ExportServiceImpl implements ExportService {

    private final CourseRepository courseRepository;
    private final ExperimentRepository experimentRepository;

    @Autowired
    public ExportServiceImpl(CourseRepository courseRepository, ExperimentRepository experimentRepository) {
        this.courseRepository = courseRepository;
        this.experimentRepository = experimentRepository;
    }

    @Override
    public byte[] exportData(List<Long> courseIds, List<Long> experimentIds, String type) throws Exception {
        // 基于type参数决定调用生成Excel还是PDF的方法
        switch (type.toLowerCase()) {
            case "excel":
                return exportToExcel(courseIds, experimentIds);
            case "pdf":
                return exportToPdf(courseIds, experimentIds);
            default:
                throw new IllegalArgumentException("Unsupported export type: " + type);
        }
    }

    private byte[] exportToExcel(List<Long> courseIds, List<Long> experimentIds) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data Export");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Course ID");
            headerRow.createCell(1).setCellValue("Course Name");
            headerRow.createCell(2).setCellValue("Experiment ID");
            headerRow.createCell(3).setCellValue("Experiment Title");

            int rowNum = 1;

            // 查询数据并填充
            List<Course> courses = courseRepository.findAllById(courseIds);
            for (Course course : courses) {
                for (Experiment experiment : course.getExperiments()) {
                    // 如果指定了experimentIds且当前experiment的ID不在列表中，则跳过
                    if (!experimentIds.isEmpty() && !experimentIds.contains(experiment.getId())) continue;

                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(course.getId());
                    row.createCell(1).setCellValue(course.getName());
                    row.createCell(2).setCellValue(experiment.getId());
                    row.createCell(3).setCellValue(experiment.getTitle());
                }
            }

            // 将Workbook写入ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private byte[] exportToPdf(List<Long> courseIds, List<Long> experimentIds) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // 设置字体以支持中文，这里需要指定一个支持中文的字体路径
        PdfFont font = PdfFontFactory.createFont();

        // 添加内容
        List<Course> courses = courseRepository.findAllById(courseIds);
        for (Course course : courses) {
            document.add(new Paragraph("Course ID: " + course.getId()).setFont(font));
            document.add(new Paragraph("Course Name: " + course.getName()).setFont(font));

            for (Experiment experiment : course.getExperiments()) {
                // 如果指定了experimentIds且当前experiment的ID不在列表中，则跳过
                if (!experimentIds.isEmpty() && !experimentIds.contains(experiment.getId())) continue;

                document.add(new Paragraph("Experiment ID: " + experiment.getId()).setFont(font));
                document.add(new Paragraph("Experiment Title: " + experiment.getTitle()).setFont(font));
            }

            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }

        document.close();
        return outputStream.toByteArray();
    }

}

