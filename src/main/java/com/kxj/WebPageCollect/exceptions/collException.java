package com.kxj.WebPageCollect.exceptions;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.exceptions
 * @Date 2022/9/16 15:25
 * @Version 1.0
 */
public class collException extends RuntimeException {
    static final long serialVersionUID = -703489719074576456L;

    public collException(){
        super();
    }

    public collException(String message) {
        super(message);
    }

    public collException(String message, Throwable cause) {
        super(message, cause);
    }

    public collException(Throwable cause) {
        super(cause);
    }

    protected collException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
