package com.denghuo.course_manage.AOP;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    int role() default 0;

    String comment();
}
