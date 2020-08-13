package com.skylab.soft_v.component;

import cn.hutool.core.util.StrUtil;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //log.info("JwtFilter-->>>isAccessAllowed");
//        if (isLoginAttempt(request,response)){
            try {
                executeLogin(request,response);
                return true;
            }catch (Exception e){
                throw new AuthenticationException("Token失效，请重新登录", e);
            }
//        }
//        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        //log.info("JwtFilter-->>>executeLogin-Method");
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(Const.ACCESS_TOKEN);
        log.info("request 接口地址：{}",req.getRequestURI());
        log.info("request 接口的请求方式{}",req.getMethod());
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request,response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //log.info("JwtFilter-->>>preHandle-Method");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        //log.info("JwtFilter-->>>isLoginAttempt-Method:返回true" + token);
        return token != null;
    }
}
