package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Admin;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.model.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDAO {

    Student stuLogin(@Param("id") int id, @Param("password") String password);

    Teacher teacherLogin(@Param("id") int id, @Param("password") String password);

    Admin adminLogin(@Param("username") String username, @Param("password") String password);



}
