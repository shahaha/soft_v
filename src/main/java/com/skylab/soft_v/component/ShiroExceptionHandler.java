package com.skylab.soft_v.component;


import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ShiroExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResultBean<Pager<?>> unauthorizedException(Exception e){
        return ResultBean.internalError("权限不足",new Pager<>(0,0,null,0));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResultBean<?> authorizationException(Exception e){
        return ResultBean.internalError("用户没有登录，请先登录");
    }

    @ExceptionHandler(BusinessException.class)
    public ResultBean<?> businessException(BusinessException e){
        return ResultBean.internalError(e.getDetailMessage());
    }

}
