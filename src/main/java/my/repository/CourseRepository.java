package my.repository;

import my.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // 查询、插入、更新、删除等操作由JPA提供
}
