package com.example.petstore.common.utils;

import com.example.petstore.example.entiey.User;
//import com.example.petstore.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器类
 */
public class MyInterceptor implements HandlerInterceptor {// 实现HandlerInterceptor接口

//    @Autowired
//    private UserService userService;
//
//    /**
//     * 访问控制器方法前执行
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        User user = userService.getByToken();
//        if (user != null) {
//            return true;
//        } else {
//            System.out.println("--preHandle:" + request.getRequestURL());
//            return false;
//        }
//    }
//
//    /**
//     * 访问控制器方法后执行
//     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//    }
//
//    /**
//     * postHandle方法执行完成后执行，一般用于释放资源
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//    }
}
