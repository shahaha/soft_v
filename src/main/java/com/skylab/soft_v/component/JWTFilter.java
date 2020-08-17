package com.skylab.soft_v.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.JWTToken;
import com.skylab.soft_v.common.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(Const.ACCESS_TOKEN);
        log.info("request 接口地址：{}",req.getRequestURI());
        log.info("request 接口的请求方式{}",req.getMethod());
        try {
            if (StringUtils.isEmpty(token)) {
                throw new BusinessException(500,"token为空!");
            }
            JWTToken jwtToken = new JWTToken(token);
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request,response).login(jwtToken);
        } catch (BusinessException e) {
            customResponse(response, e.getDetailMessage());
            return false;
        } catch (AuthenticationException e) {
            if (e.getCause() instanceof BusinessException) {
                BusinessException exception = (BusinessException) e.getCause();
                customResponse(response,  exception.getDetailMessage());
            } else {
                customResponse(response, "用户未登录，请重新登录");
            }
            return false;
        } catch (Exception e) {
            customResponse(response, "系统出错");
            return false;
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

//    @Override
//    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
//        //log.info("JwtFilter-->>>executeLogin-Method");
//
//    }

//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        log.info("JwtFilter-->>>preHandle-Method");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
//        return super.preHandle(request, response);
//    }

    /**
     * 自定义响应前端
     *
     * @param response
     * @param msg
     * @return void
     * @throws
     * @Author: 小霍
     * @UpdateUser:
     * @Version: 0.0.1
     */
    private void customResponse(ServletResponse response, String msg) {
        try {
            ResultBean<?> result = ResultBean.internalError(msg);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String str = objectMapper.writeValueAsString(result);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            log.error("customResponse...error:{}", e);
        }

    }
}
