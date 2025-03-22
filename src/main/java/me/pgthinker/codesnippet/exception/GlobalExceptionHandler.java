package me.pgthinker.codesnippet.exception;

import lombok.extern.slf4j.Slf4j;
import me.pgthinker.codesnippet.common.BaseResponse;
import me.pgthinker.codesnippet.common.ErrorCode;
import me.pgthinker.codesnippet.common.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Project: me.pgthinker.codesnippet.exception
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/22 13:07
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }
}
