package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.AdminDAO;
import com.denghuo.course_manage.model.Admin;
import com.denghuo.course_manage.service.AdminService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Boolean updateAdmin(Admin admin) {
        if(admin.getPassword()!=null){
            admin.setPassword(MD5util.getMD5String(admin.getPassword()));
        }
        if(1!=adminDAO.updateAdmin(admin)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public Boolean insertAdmin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(MD5util.getMD5String(password));
        if(1!=adminDAO.insertAdmin(admin)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public Object getAdmin(Admin admin,Integer pageNum,Integer pageSize) {
        List<Admin> adminList = adminDAO.getAdmin(admin, (pageNum - 1) * pageSize, pageSize);
        Double totalCount = adminDAO.getAdminTotal(admin);
        Double totalPage = Math.ceil(totalCount/pageSize);
        return Result.send(new String[]{"totalCount","totalPage","adminList"},totalCount,totalPage,adminList);
    }

    @Override
    public Boolean deleteAdmin(Integer id) {
        if(1!=adminDAO.deleteAdmin(id)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }
}
