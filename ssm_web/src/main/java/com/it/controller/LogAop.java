package com.it.controller;

import com.it.domain.SysLog;
import com.it.service.SysLogService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    @Before("execution(* com.it.controller.*.*(..))")
    public void beforeExecute(JoinPoint joinPoint) throws NoSuchMethodException {
        clazz = joinPoint.getTarget().getClass();
        if (!clazz.getName().contains("SysLogController")){
        visitTime=new Date();
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        Class[] argsClzz=new Class[args.length];
      if(args==null||args.length==0){
          method=clazz.getMethod(methodName);
      }else {
          for (int i = 0; i < args.length; i++) {
          argsClzz[i]=args[i].getClass();
      }
         method= clazz.getMethod(methodName,argsClzz);
      }
        }
    }
    @After("execution(* com.it.controller.*.*(..))")
    public void afterExecute(){
        if (!clazz.getName().contains("SysLogController")){
        SysLog sysLog=new SysLog();
        sysLog.setMethod("访问类："+clazz.getName()+"下的方法："+method.getName());
        sysLog.setExecutionTime(new Date().getTime()-visitTime.getTime());

        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    //将日志相关信息封装到SysLog对象
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                }
            }
        }
            //调用Service完成操作
            sysLogService.save(sysLog);


        }
    }
}
