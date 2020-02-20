package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.DTO.CourseScoreDTO;
import com.denghuo.course_manage.VO.CourseScoreVO;
import com.denghuo.course_manage.dao.SelectCourseDAO;
import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.service.StudentService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Result;
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
        String gender = student.getGender();
        if(!gender.equals("男")&&!gender.equals("女")){
            throw new CustomException(MyExceptionEnum.param_error);
        }
        gender = gender.equals("男")?"male":"female";
        student.setGender(gender);
        if(student.getPassword()==null){
            student.setPassword(MD5util.getMD5String(student.getId()));
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
    public Object getStuInfo(Student student, Integer pageNum, Integer pageSize) {
        String name = student.getName();
        if(name!=null&&name.equals("")){
            student.setName(null);
        }
        Double totalCount = studentDAO.getStuInfoTotal(student);
        Double totalPage = Math.ceil(totalCount/pageSize);
        List<Student> stuInfos = studentDAO.getStuInfo(student, (pageNum - 1) * pageSize, pageSize);
        return Result.send(new String[]{"totalCount","totalPage","stuInfos"},totalCount,totalPage,stuInfos);
    }

    @Override
    public Object getStuCourse(CourseScoreDTO courseScoreDTO, Integer pageNum, Integer pageSize) {
        //查询课程
        List<CourseScoreVO> courseList = selectCourseDAO.getCourseByCondition(courseScoreDTO, (pageNum - 1) * pageSize, pageSize);
        //查询total
        Double totalCount = selectCourseDAO.getCourseTotalByCondition(courseScoreDTO);
        Double totalPage = Math.ceil(totalCount/pageSize);
        return Result.send(new String[]{"courseList","totalCount","totalPage"},courseList,totalCount,totalPage);

    }
}
