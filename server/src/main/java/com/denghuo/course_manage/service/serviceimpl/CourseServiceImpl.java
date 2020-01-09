package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.CourseDAO;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.service.CourseService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public boolean updateCourse(Course course) {
        if(1!=courseDAO.updateCourse(course)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public List<Course> getCourse(Course course,Integer pageNum,Integer pageSize) {
        return courseDAO.getCourse(course,(pageNum-1)*pageSize,pageSize);
    }

    @Override
    public boolean deleteCourse(String id) {
        if(1!=courseDAO.deleteCourse(id)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public boolean insertCourse(Course course) {
        if(1!=courseDAO.insertCourse(course)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

}
