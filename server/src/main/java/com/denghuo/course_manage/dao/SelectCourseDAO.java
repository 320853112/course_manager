package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.DTO.CourseScoreDTO;
import com.denghuo.course_manage.VO.CourseScoreVO;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.StuToCourse;
import com.sun.org.apache.bcel.internal.generic.INEG;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectCourseDAO {
    int pickCourse(@Param("stu_id") String stuId,@Param("course_id") String courseId);

    int reduceCourse(String courseId);

    List<CourseScoreVO> getCourseByCondition(@Param("courseScoreDTO") CourseScoreDTO courseScoreDTO, @Param("startNum")Integer startNum, @Param("pageSize")Integer pageSize);

    Double getCourseTotalByCondition(@Param("courseScoreDTO") CourseScoreDTO courseScoreDTO);

    int setScoreByStu(StuToCourse stuToCourse);
}
