package com.kxj.WebPageCollect.annotation;

import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.*;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.annotation
 * @Date 2022/10/27 15:28
 * @Version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Login {
}
