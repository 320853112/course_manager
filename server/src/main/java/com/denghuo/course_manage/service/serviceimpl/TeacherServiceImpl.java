package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.VO.StudentCourseVO;
import com.denghuo.course_manage.VO.TeacherVO;
import com.denghuo.course_manage.dao.CourseDAO;
import com.denghuo.course_manage.dao.SelectCourseDAO;
import com.denghuo.course_manage.dao.TeacherDAO;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.Teacher;
import com.denghuo.course_manage.service.TeacherService;
import com.denghuo.course_manage.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private SelectCourseDAO selectCourseDAO;

    @Override
    public boolean updateTeacher(Teacher teacher) {
        if(teacher.getPassword()!=null){
            teacher.setPassword(MD5util.getMD5String(teacher.getPassword()));
        }
        if(1!=teacherDAO.updateTeacher(teacher)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }

    @Override
    public Object getTeacher(Teacher teacher, Integer pageNum, Integer pageSize, HttpSession session) {
        String name = teacher.getName();
        if(name!=null&&teacher.getName().equals("")){
            teacher.setName(null);
        }
        Double totalCount = teacherDAO.getTeacherTotal(teacher);
        Double totalPage = Math.ceil(totalCount/pageSize);
        List<Teacher> teachers = teacherDAO.getTeacher(teacher, (pageNum - 1) * pageSize, pageSize);
        Object userId = session.getAttribute("userId");
        List<TeacherVO> teacherVOs = new ArrayList<>();
        for (Teacher t : teachers) {
            teacherVOs.add(t.toTeacherVO(userId));
        }
        return Result.send(new String[]{"totalCount","totalPage","teachers"},totalCount,totalPage,teacherVOs);
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

    @Override
    public Object getTeacherCourse(Integer pageNum, Integer pageSize, HttpSession session) {
        Integer roleNum = (Integer) session.getAttribute("roleNum");
        if(roleNum!= RoleData.teacher.getRoleNum()){
            throw new CustomException(MyExceptionEnum.AUTHORITY_LACK);
        }
        String teacherId = (String) session.getAttribute("userId");
        Course course = new Course();
        course.setTeacherId(teacherId);
        List<Course> courses = courseDAO.getCourse(course, (pageNum - 1) * pageSize, pageSize);
        Double totalCount = courseDAO.getCourseTotal(course);
        Double totalPage = Math.ceil(totalCount/pageSize);
        return Result.send(new String[]{"totalCount","totalPage","courses"},totalCount,totalPage,courses);
    }

    @Override
    public Object getStuByCourse(String teacherId,String courseName, Integer pageNum, Integer pageSize) {
        List<StudentCourseVO> studentCourse = selectCourseDAO.getStuByCourse(teacherId,courseName, (pageNum - 1) * pageSize, pageSize);
        Double totalCount = selectCourseDAO.getStuByCourseTotal(teacherId,courseName);
        Double totalPage = Math.ceil(totalCount/pageSize);
        return Result.send(new String[]{"totalCount","totalPage","studentCourse"},totalCount,totalPage,studentCourse);
    }
}
