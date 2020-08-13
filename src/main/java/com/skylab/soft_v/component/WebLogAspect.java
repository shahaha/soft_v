package com.skylab.soft_v.component;


import com.skylab.soft_v.entity.Log;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.service.LogService;
import com.skylab.soft_v.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Resource
    private LogService logService;

    @Pointcut("@annotation(com.skylab.soft_v.component.ActionLog)")
    public void logPointCut(){}

    @AfterReturning("logPointCut()")
    public void saveLog(JoinPoint joinPoint){
        log.info("WebLogAspect-->>>saveLog");
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)attributes).getRequest();
        Log log1 = new Log();
        log1.setTime(new Date());
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            log1.setRealName(null);
        }else {
            log1.setRealName(user.getRealName());
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ActionLog actionLog = method.getAnnotation(ActionLog.class);
        if (actionLog != null){
            log1.setDes(actionLog.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        log1.setMethod(className + "." + methodName);
        log1.setIp(IpUtils.getIpAddr(request));
        try {
            logService.insertSelective(log1);
        }catch (Exception e){
            System.out.println("日志管理切面异常:"+e.getMessage());
        }

    }

}
