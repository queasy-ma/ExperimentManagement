package my.dto;

import java.util.List;

public class AllDataDto {
    private List<CourseDto> courses;

    // 仅需一个构造函数来初始化课程列表
    public AllDataDto(List<CourseDto> courses) {
        this.courses = courses;
    }

    // Getter 和 Setter 方法
    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }
}
