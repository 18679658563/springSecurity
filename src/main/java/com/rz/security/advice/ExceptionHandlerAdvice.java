package com.rz.security.advice;

import com.rz.security.dto.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * 统一异常处理
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-29
 * Time: 下午2:59
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger log = LoggerFactory.getLogger("adminL logogger");

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo badResquestException(IllegalArgumentException exception){
        return new ResponseInfo(HttpStatus.BAD_REQUEST.value()+"",exception.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseInfo badRequestException(AccessDeniedException exception){
        return new ResponseInfo(HttpStatus.FORBIDDEN.value()+"",exception.getMessage());
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
            UnsatisfiedServletRequestParameterException.class, MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseInfo badRequestException(Exception exception) {
        return new ResponseInfo(HttpStatus.BAD_REQUEST.value() + "", exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseInfo exception(Throwable throwable) {
        log.error("系统异常", throwable);
        return new ResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", throwable.getMessage());

    }

}
