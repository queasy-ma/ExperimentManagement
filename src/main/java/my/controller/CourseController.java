package my.controller;

import my.dto.CourseDto;
import my.model.Course;
import my.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // API端点，用于添加一个新课程
    @PostMapping("/add")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        Course course = courseService.addCourse(courseDto);
        return new ResponseEntity<>(new CourseDto(course.getId(), course.getName(), course.getDescription()), HttpStatus.CREATED);
    }

    // API端点，用于删除一个课程
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}