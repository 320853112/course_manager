package com.denghuo.course_manage.VO;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author: xuhang
 * @Date: 2020/4/20 13:51
 * @Description:
 **/
@Data
public class CourseTableVO {
    private String classesTime;
    private HashMap monday;
    private HashMap tuesday;
    private HashMap wednesday;
    private HashMap thursday;
    private HashMap friday;
    private HashMap saturday;
    private HashMap sunday;

    public CourseTableVO(String classesTime) {
        this.classesTime = classesTime;
    }
}
