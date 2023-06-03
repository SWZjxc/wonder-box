package com.wonderbox.exception;

/**
 * 自定义异常类
 *
 * @author Ge Mingjia
 */
public class ExceptionBox extends RuntimeException {
    private final int code;

    public ExceptionBox(int code, String message) {
        super(message);
        this.code = code;
    }

    public ExceptionBox(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public ExceptionBox(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }

}
