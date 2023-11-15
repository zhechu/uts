package com.yly.uts.core.exception;


import com.yly.uts.core.common.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 其它所有异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ReturnT exceptionHandler(Exception e) {
        log.error("error", e);

        StringJoiner message = new StringJoiner(", ");
        if (e instanceof BindException) {
            List<ObjectError> allErrors = ((BindException) e).getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                for (ObjectError objectError : allErrors) {
                    if (objectError != null && objectError.getDefaultMessage() != null) {
                        message.add(objectError.getDefaultMessage());
                    }
                }
            }
            return new ReturnT(ReturnT.FAIL_CODE,  message.toString());
        }


        if (e instanceof MethodArgumentNotValidException) {
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                for (ObjectError objectError : allErrors) {
                    if (objectError != null && objectError.getDefaultMessage() != null) {
                        message.add(objectError.getDefaultMessage());
                    }
                }
            }

            return new ReturnT(ReturnT.FAIL_CODE,  message.toString());
        }

        // 接收类型异常
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException error = ((MethodArgumentTypeMismatchException) e);
            message.add(error.getMessage());
            return new ReturnT(ReturnT.FAIL_CODE,  message.toString());
        }

        if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
            if (!CollectionUtils.isEmpty(violations)) {
                violations.forEach(violation -> {
                    message.add(violation.getMessage());
                });
            }

            return new ReturnT(ReturnT.FAIL_CODE,  message.toString());
        }

        return ReturnT.FAIL;
    }

    /**
     * 参数属性缺失异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ReturnT missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("error", e);

        return new ReturnT(ReturnT.FAIL_CODE,  e.getMessage());
    }

    /**
     * 请求参数转换异常
     * @param httpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ReturnT httpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        log.error("error:", httpMessageNotReadableException);

        return ReturnT.FAIL;
    }

}
