package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.CourseDAO;
import com.denghuo.course_manage.dao.SelectCourseDAO;
import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.StuToCourse;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.service.CourseService;
import com.denghuo.course_manage.service.SelectCourseService;
import com.denghuo.course_manage.service.StudentService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Result;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SelectCourseServiceImpl implements SelectCourseService {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SelectCourseDAO selectCourseDAO;
    @Autowired
    private CourseDAO courseDAO;

    @Override
    @Transactional
    public boolean pickCourse(String stuId, String courseId) {
        //TODO  防止重复选课
        //先查询验证学生和课程是否都存在
        List<Student> stuInfo = studentDAO.getStuInfo(new Student(stuId),0,1);
        Course course = courseDAO.existCourse(courseId);
        if (stuInfo.size() != 1 || course==null) {
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        synchronized (this) {

            //再验证是否有剩余的课程
            if (course.getSurplus() <= 0) {
                throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
            }
            //使用锁来防止超卖
            if (selectCourseDAO.pickCourse(stuId, courseId) != 1) {
                throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
            }
            //选完课之后，将课程剩余量减少
            selectCourseDAO.reduceCourse(courseId);
        }
        return true;
    }

    @Override
    public boolean setScoreByStu(StuToCourse stuToCourse) {
        if(1!=selectCourseDAO.setScoreByStu(stuToCourse)){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }
}
