package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDAO {
    Integer updateAdmin(Admin admin);

    Integer insertAdmin(Admin admin);

    List<Admin> getAdmin(@Param("admin")Admin admin,@Param("startNum")Integer startNum, @Param("pageSize") Integer pageSize);

    Integer deleteAdmin(Integer id);


}
