//package com.EMT.application;
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletResponse;
//
//
//public class testInterceptor extends HandlerInterceptorAdapter {
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Cache-Control");
//        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE,PATCH");
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//        response.setHeader("Cache-Control", "no-cache, no-store");
//        return true;
//    }
//}
