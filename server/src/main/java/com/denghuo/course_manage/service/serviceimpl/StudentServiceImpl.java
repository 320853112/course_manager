package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.SelectCourseDAO;
import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.service.StudentService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private SelectCourseDAO selectCourseDAO;

    @Override
    public Boolean insertStuInfo(Student student) {
        if(student.getPassword()==null){
            student.setPassword(MD5util.getMD5String(student.getId().toString()));
        }else{
            student.setPassword(MD5util.getMD5String(student.getPassword()));
        }
        Integer res = studentDAO.insertStuInfo(student);
        if(res==1) {
            return true;
        }else{
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
    }

    @Override
    public List<Student> getStuInfo(Student student, Integer pageNum, Integer pageSize) {
        return studentDAO.getStuInfo(student,(pageNum-1)*pageSize,pageSize);
    }

    @Override
    public List<Course> getStuCourse(Integer stuId, Integer pageNum,Integer pageSize) {
        //查询选课表
        return selectCourseDAO.getCourseByStuId(stuId,(pageNum-1)*pageSize,pageSize);
    }
}
