package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Student;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO {
    List<Student> getStuInfo(@Param("student")Student student, @Param("startNum")Integer startNum, @Param("pageSize")Integer pageSize);

    Double getStuInfoTotal(Student student);

    Integer updateStuInfo(Student student);

    Integer deleteStuInfo(Integer id);

    Integer insertStuInfo(Student student);
}
