package com.kxj.WebPageCollect.interceptor;

import com.kxj.WebPageCollect.annotation.Login;
import com.kxj.WebPageCollect.entity.UserEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.RetentionPolicy;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.interceptor
 * @Date 2022/10/27 15:27
 * @Version 1.0
 */
public class AutoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取session user对象
        UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");

        //判断方法上是否有@Login注解
        Login annotation;
        if(handler instanceof HandlerMethod){
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else {
            return true;
        }

        if(annotation==null){
            return true;
        }

        if(userentity==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }

        return true;
    }
}
