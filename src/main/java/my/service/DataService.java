package my.service;

import my.dto.AllDataDto;
import my.dto.CourseDto;
import my.dto.ExperimentContentDto;
import my.dto.ExperimentDto;
import my.model.Course;
import my.model.Experiment;
import my.model.ExperimentContent;
import my.repository.CourseRepository;
import my.repository.ExperimentContentRepository;
import my.repository.ExperimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final CourseRepository courseRepository;
    private final ExperimentRepository experimentRepository;
    private final ExperimentContentRepository experimentContentRepository;

    @Autowired
    public DataService(CourseRepository courseRepository, ExperimentRepository experimentRepository,
                       ExperimentContentRepository experimentContentRepository) {
        this.courseRepository = courseRepository;
        this.experimentRepository = experimentRepository;
        this.experimentContentRepository = experimentContentRepository;
    }

    public AllDataDto getAllData() {
        // 查询所有课程
        List<Course> courses = courseRepository.findAll();

        // 准备一个空的列表来收集所有的课程DTO
        ArrayList<CourseDto> courseDtos = new ArrayList<>();

        // 遍历每个课程
        for (Course course : courses) {
            // 创建课程DTO
            CourseDto courseDto = new CourseDto(course.getId(), course.getName(), course.getDescription());

            // 查询与当前课程相关联的所有实验
            List<Experiment> experiments = experimentRepository.findByCourse_Id(course.getId());

            // 遍历每个实验
            for (Experiment experiment : experiments) {
                // 创建实验DTO
                ExperimentDto experimentDto = new ExperimentDto(experiment.getId(), experiment.getTitle(), experiment.getType(), experiment.getCourseId());

                // 查询与当前实验相关联的所有实验内容
                List<ExperimentContent> contents = experimentContentRepository.findByExperiment_Id(experiment.getId());

                // 遍历每个实验内容
                for (ExperimentContent content : contents) {
                    // 创建实验内容DTO
                    ExperimentContentDto contentDto = new ExperimentContentDto(content.getId(), content.getDetails(), content.getExperimentId());

                    // 将实验内容DTO添加到实验DTO
                    experimentDto.addExperimentContent(contentDto);
                }

                // 将实验DTO添加到课程DTO
                courseDto.addExperiment(experimentDto);
            }

            // 将课程DTO添加到课程DTO列表
            courseDtos.add(courseDto);
        }

        // 创建并返回包含所有课程DTO的AllDataDto对象
        return new AllDataDto(courseDtos);
    }

}

