package com.denghuo.course_manage.model;


import com.denghuo.course_manage.VO.CourseVO;
import lombok.Data;

@Data
public class Course {
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
    private String classesTime;

    public Course() {
    }
    public Course(String id) {
        this.id = id;
    }

    public CourseVO toCourseVO(Object userId){
        CourseVO courseVO = new CourseVO();
        courseVO.setId(id);
        courseVO.setCategory(category);
        courseVO.setName(name);
        courseVO.setTimeWeek(timeWeek);
        courseVO.setCredit(credit);
        courseVO.setTeacher(teacher);
        courseVO.setSurplus(surplus);
        courseVO.setStartTime(startTime);
        courseVO.setEndTime(endTime);
        courseVO.setCheckType(checkType);
        courseVO.setStudyType(studyType);
        courseVO.setPlace(place);
        courseVO.setTeacherId(teacherId);
        courseVO.setUserId(userId);
        courseVO.setClassesTime(classesTime);
        return courseVO;
    }
}
