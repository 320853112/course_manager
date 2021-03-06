package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDAO {

    List<Teacher> getTeacher(@Param("teacher")Teacher teacher,@Param("startNum") Integer startNum,@Param("pageSize")Integer pageSize);

    Double getTeacherTotal(Teacher teacher);

    int insertTeacher(Teacher teacher);

    int deleteTeacher(Integer id);

    int updateTeacher(Teacher teacher);

    Course getTeacherCourse(@Param("teacherId")String teacherId,@Param("startNum") Integer startNum,@Param("pageSize")Integer pageSize);
}
