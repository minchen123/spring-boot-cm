package com.cm.scaffold.common.exception;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/9/8 16:28
 */
public class DemoException extends RuntimeException {

    private Integer code;

    private String message;

    public DemoException(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public DemoException(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
