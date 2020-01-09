package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.StuToCourse;
import com.sun.org.apache.bcel.internal.generic.INEG;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectCourseDAO {
    int pickCourse(@Param("stu_id") String stuId,@Param("course_id") int courseId);

    int reduceCourse(Integer courseId);

    List<Course> getCourseByStuId(@Param("stu_id")String stu_id,@Param("startNum")Integer startNum,@Param("pageSize")Integer pageSize);

    int setScoreByStu(StuToCourse stuToCourse);
}
