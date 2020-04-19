package com.denghuo.course_manage.VO;

import lombok.Data;

/**
 * @Author: xuhang
 * @Date: 2020/4/19 20:26
 * @Description:
 **/
@Data
public class StudentCourseVO {

    private String stuId;
    private String stuName;
    private String className;
    private String courseName;
    private String courseId;
    private Float score;
}
