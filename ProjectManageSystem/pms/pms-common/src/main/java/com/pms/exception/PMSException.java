package com.pms.exception;


import com.pms.base.entity.ReturnDTO;
import com.pms.enums.HttpCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 公共 业务 异常类
 */
public class PMSException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected String error;
    protected int code;
    protected ReturnDTO returnDTO;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ReturnDTO getReturnDTO() {
        return returnDTO;
    }

    public void setReturnDTO(ReturnDTO returnDTO) {
        this.returnDTO = returnDTO;
    }

    public PMSException(ReturnDTO returnDTO) {
        super(returnDTO.getError());
        this.returnDTO = returnDTO;
    }

    public PMSException(String error) {
        super(error);
        this.code = HttpCodeEnum.UN_KNOW_ERROR.getCode();
        this.error = error;
        this.returnDTO = new ReturnDTO(code, error);
    }

    public PMSException(int code, String error) {
        super(error);
        this.code = code;
        this.error = error;
        this.returnDTO = new ReturnDTO(code, error);
    }

    public PMSException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMessage());
        this.code = httpCodeEnum.getCode();
        this.error = httpCodeEnum.getMessage();
        this.returnDTO = new ReturnDTO(code, error);
    }
}
