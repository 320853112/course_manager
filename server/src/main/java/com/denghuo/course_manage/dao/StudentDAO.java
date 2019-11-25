package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

public interface StudentDAO {
    Student getStuInfo(Integer id);
}
