package com.denghuo.course_manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDAO {

    Integer stuLogin(@Param("id") int id, @Param("password") String password);

    Integer teacherLogin(@Param("id") int id, @Param("password") String password);

    Integer adminLogin(@Param("username") int username, @Param("password") String password);



}
