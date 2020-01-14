package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.StuToCourse;

public interface SelectCourseService {
    boolean pickCourse(String stuId,String courseId);

    boolean removeCourse(String stuId,String courseId);

    boolean setScoreByStu(StuToCourse stuToCourse);


}
