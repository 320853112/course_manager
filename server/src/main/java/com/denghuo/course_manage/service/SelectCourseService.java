package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.StuToCourse;

public interface SelectCourseService {
    boolean pickCourse(String stuId,int courseId);

    boolean setScoreByStu(StuToCourse stuToCourse);
}
