package com.denghuo.course_manage.service.serviceimpl;

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

    @Override
    @Transactional
    public boolean pickCourse(String stuId, int courseId) {
        //先查询验证学生和课程是否都存在
        List<Student> stuInfo = studentDAO.getStuInfo(new Student(stuId),1,1);
        Course course = new Course(courseId);
        List<Course> courses = courseService.getCourse(course,1,1);
        if (stuInfo.size() != 1 || courses.size() !=1) {
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        synchronized (this) {

            //再验证是否有剩余的课程
            if (courses.get(0).getSurplus() <= 0) {
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
