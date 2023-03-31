package com.wzz.satoken.Exception;

public class BusinessException extends  Exception{

    private static final long serialVersionUID = 682055386820597280L;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
