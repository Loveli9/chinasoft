package com.pms.exception;


import com.pms.base.entity.ReturnDTO;
import com.pms.enums.HttpCodeEnum;
import com.pms.utils.ReturnDTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * 通用异常处理类
 */
@ControllerAdvice
@ResponseBody
public class PMSExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());





        @ExceptionHandler(BindException.class)
        public  ResponseEntity<ReturnDTO> bindExceptionHandler(BindException e){
            return new ResponseEntity(ReturnDTOUtil.custom(HttpCodeEnum.INVALID_REQUEST.getCode(),e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }


   
    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(PMSException.class)
    public ResponseEntity<ReturnDTO> handleBlifeException(PMSException e) {
        logger.info(e.getError());
        return new ResponseEntity(e.getReturnDTO(), HttpStatus.OK);
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ReturnDTO> handleHttpMessageNotReadableException(Exception e) {

        return general(HttpStatus.BAD_REQUEST,e);

    }

    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ReturnDTO> handleHttpRequestMethodNotSupportedException(Exception e) {
        return general(HttpStatus.METHOD_NOT_ALLOWED,e);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ReturnDTO> handleHttpMediaTypeNotSupportedException(Exception e) {

        return general(HttpStatus.UNSUPPORTED_MEDIA_TYPE,e);

    }

    /**
     * 500 - Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ReturnDTO> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity(ReturnDTOUtil.error(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * sql 异常
     * @param e
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ReturnDTO> handleSql(Exception e){
        return general(HttpStatus.INTERNAL_SERVER_ERROR,e);
    }

    /**
     * 返回 spring 统一异常 后的信息
     * @param httpStatus
     * @return
     */
    private ResponseEntity<ReturnDTO> general(HttpStatus httpStatus, Exception e){
        e.printStackTrace();
        return new ResponseEntity(ReturnDTOUtil.custom(httpStatus.value(), httpStatus.name()), httpStatus);
    }

}
