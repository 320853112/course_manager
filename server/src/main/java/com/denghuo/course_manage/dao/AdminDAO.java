package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDAO {
    Integer updateAdmin(Admin admin);

    Integer insertAdmin(Admin admin);

    List<Admin> getAdmin(String username);

    Integer deleteAdmin(Integer id);


}
