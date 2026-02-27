package com.kxj.WebPageCollect.ATO;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.ATO
 * @Date 2022/9/11 19:53
 * @Version 1.0
 */

import lombok.Data;

import java.util.Map;

@Data
public class ResponseBeanCode {

     Integer code;
     String message;
     Object data;

    public ResponseBeanCode(Integer code, String message, Object map) {
        this.code=code;
        this.message=message;
        this.data=map;
    }

    public static ResponseBeanCode success(Object data){
        return new ResponseBeanCode(MyCode.SUCCEED.getCode(),MyCode.SUCCEED.getMessage(),data);
    }

    public static ResponseBeanCode error(){
        return new ResponseBeanCode(MyCode.ERROR.getCode(),MyCode.ERROR.getMessage(),null);
    }

}
