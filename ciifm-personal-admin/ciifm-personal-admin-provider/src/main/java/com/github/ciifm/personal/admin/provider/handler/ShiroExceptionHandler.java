package com.github.ciifm.personal.admin.provider.handler;

import com.github.ciifm.handy.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/20 0020 09:49
 */
@Slf4j
@RestControllerAdvice
public class ShiroExceptionHandler {

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.OK)//200
    public ResponseData unknownAccountExceptionHandler(UnknownAccountException e) {
        log.debug("Exception occurred while processing your request : Cause by " + e, e);
        return new ResponseData(1103, "无效账户名");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.OK)//200
    public ResponseData UnauthorizedExceptionHandler(UnauthorizedException e) {
        log.debug("Exception occurred while processing your request : Cause by " + e, e);
        return new ResponseData(1202, "没有操作权限");
    }
}
