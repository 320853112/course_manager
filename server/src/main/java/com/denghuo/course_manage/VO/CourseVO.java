package com.denghuo.course_manage.VO;

import lombok.Data;

/**
 * @Author: xuhang
 * @Date: 2020/2/21 14:38
 * @Description:
 **/
@Data
public class CourseVO {
    private Object userId;
    private String id;
    private String category;
    private String name;
    private String timeWeek;
    private String credit;
    private String teacher;
    private Integer surplus;
    private String startTime;
    private String endTime;
    private String checkType;
    private String studyType;
    private String place;
    private String teacherId;
}
