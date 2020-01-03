package com.denghuo.course_manage.AOP;

import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;

@Component
@Aspect
public class AccessInterceptor {


    @Autowired
    private HttpSession session;


    @Around("@within(access)")
    public Object verifyAuthority(ProceedingJoinPoint pjp,Access access) throws Throwable{
        //校验权限
        Integer role = (Integer)session.getAttribute("role");
        if(role ==null) {
            throw new CustomException(MyExceptionEnum.PLEASE_LOGIN);
        }
        int askRole = access.role();
        if(role<askRole){
            throw new CustomException(MyExceptionEnum.AUTHORITY_LACK);
        }
        return pjp.proceed();
    }
}
