package com.kxj.WebPageCollect.ATO;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.ATO
 * @Date 2022/9/11 19:50
 * @Version 1.0
 */

public enum MyCode {

    SUCCEED(200,"请求成功"),
    ERROR(400,"请求失败");


    private Integer code;
    private String message;

    MyCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
