package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectCourseDAO {
    int pickCourse(@Param("stu_id") int stuId,@Param("course_id") int courseId);

    int reduceCourse(int courseId);

    List<Course> getCourseByStuId(Integer stu_id);
}
