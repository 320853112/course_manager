package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Student;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO {
    List<Student> getStuInfo(Student student);

    Integer updateStuInfo(Student student);

    Integer deleteStuInfo(Integer id);

    Integer insertStuInfo(Student student);
}
