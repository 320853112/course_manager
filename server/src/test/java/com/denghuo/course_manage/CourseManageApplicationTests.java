package com.denghuo.course_manage;

import com.denghuo.course_manage.utils.MD5util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseManageApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5util.getMD5String("12"));
    }

}
