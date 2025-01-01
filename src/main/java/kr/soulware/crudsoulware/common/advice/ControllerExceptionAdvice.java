package kr.soulware.crudsoulware.common.advice;

import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.model.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BAD_REQUEST
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        log.error("Validation error for field {}: {}", fieldError.getField(), fieldError.getDefaultMessage());
        return BaseResponse.error(
                ErrorCode.VALIDATION_REQUEST_MISSING_EXCEPTION,
                String.format("%s (%s)", fieldError.getDefaultMessage(), fieldError.getField())
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected BaseResponse handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        log.error("Missing Request Header: {}", e.getMessage());
        return BaseResponse.error(
                ErrorCode.VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION,
                String.format("%s (%s)",
                        ErrorCode.VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION.getMessage(),
                        e.getHeaderName()
                ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected BaseResponse handleMissingRequestParameterException(final MissingServletRequestParameterException e) {
        log.error("Missing Request Parameter: {}", e.getMessage());
        return BaseResponse.error(
                ErrorCode.VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION,
                String.format("%s (%s)",
                        ErrorCode.VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION.getMessage(),
                        e.getParameterName()
                ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected BaseResponse handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("Http Request Method Not Supported: {}", e.getMessage());
        return BaseResponse.error(ErrorCode.REQUEST_METHOD_VALIDATION_EXCEPTION, e.getMessage());
    }

    /**
     * 500 INTERNAL_SERVER_ERROR
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected BaseResponse handleException(final Exception e) {
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return BaseResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * CUSTOM EXCEPTION
     */
    @ExceptionHandler(CustomException.class)
    protected BaseResponse handleCustomException(CustomException e) {
        log.error("Custom Exception: {}", e.getMessage(), e);
        return BaseResponse.error(e.getErrorCode(), e.getMessage());
    }
}
