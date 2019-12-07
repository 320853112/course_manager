package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.AdminDAO;
import com.denghuo.course_manage.model.Admin;
import com.denghuo.course_manage.service.AdminService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Boolean updateAdmin(Admin admin) {
        if(admin.getPassword()!=null){
            admin.setPassword(MD5util.getMD5String(admin.getPassword()));
        }
        if(adminDAO.updateAdmin(admin)==1){
            return true;
        }else {
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }

    }
}
