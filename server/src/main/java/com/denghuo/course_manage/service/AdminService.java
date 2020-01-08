package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Admin;

import java.util.List;

public interface AdminService {
    Boolean updateAdmin(Admin admin);

    Boolean insertAdmin(String username,String password);

    List<Admin> getAdmin(Admin admin,Integer pageNum,Integer pageSize);

    Boolean deleteAdmin(Integer id);


}
