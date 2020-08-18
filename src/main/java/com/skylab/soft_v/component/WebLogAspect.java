package com.skylab.soft_v.component;


import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.entity.Log;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.service.LogService;
import com.skylab.soft_v.util.IpUtils;
import com.skylab.soft_v.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
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

    @Around("logPointCut()")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("WebLogAspect-->>>saveLog");
        Log log1 = new Log();
        log1.setTime(new Date());
        Object o = joinPoint.proceed();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ActionLog actionLog = method.getAnnotation(ActionLog.class);
        if (actionLog != null){
            log1.setDes(actionLog.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        log1.setMethod(className + "." + methodName);
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)attributes).getRequest();
        log1.setIp(IpUtils.getIpAddr(request));
        String token = request.getHeader(Const.ACCESS_TOKEN);
        if (token != null){
            String username = JwtTokenUtil.getUserId(token);
            log1.setRealName(username);
        }
        try {
            logService.insertSelective(log1);
        }catch (Exception e){
            System.out.println("日志管理切面异常:"+e.getMessage());
        }
        return o;
    }

}
