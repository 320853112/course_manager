package com.denghuo.course_manage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDAO {

    String stuLogin(@Param("id") int id, @Param("password") String password);

    String teacherLogin(@Param("id") int id, @Param("password") String password);

    String adminLogin(@Param("username") String username, @Param("password") String password);



}
