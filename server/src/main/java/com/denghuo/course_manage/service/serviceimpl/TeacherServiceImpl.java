package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.TeacherDAO;
import com.denghuo.course_manage.model.Teacher;
import com.denghuo.course_manage.service.TeacherService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;
    @Override
    public boolean updateTeacher(Teacher teacher) {
        if(1!=teacherDAO.updateTeacher(teacher)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public Object getTeacher(Teacher teacher,Integer pageNum,Integer pageSize) {
        Double total = teacherDAO.getTeacherTotal(teacher);
        total = Math.ceil(total/pageSize);
        List<Teacher> teachers = teacherDAO.getTeacher(teacher, (pageNum - 1) * pageSize, pageSize);
        return Result.send(new String[]{"total","teachers"},total,teachers);
    }

    @Override
    public boolean deleteTeacher(Integer id) {
        if(1!=teacherDAO.deleteTeacher(id)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        if(teacher.getPassword()==null){
            teacher.setPassword(MD5util.getMD5String("123456"));
        }else{
            teacher.setPassword(MD5util.getMD5String(teacher.getPassword()));
        }
        if(1!=teacherDAO.insertTeacher(teacher)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }
}
