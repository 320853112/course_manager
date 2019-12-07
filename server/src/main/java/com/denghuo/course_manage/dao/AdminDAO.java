package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO {
    Integer updateAdmin(Admin admin);
}
