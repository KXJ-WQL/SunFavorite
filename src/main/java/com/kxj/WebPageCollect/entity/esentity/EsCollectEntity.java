package com.kxj.WebPageCollect.entity.esentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.entity.esentity
 * @Date 2022/9/19 23:07
 * @Version 1.0
 */
@Data
@Document(indexName = "kxjes_collect",createIndex = true)
public class EsCollectEntity {

    @Id
    private Integer id;

    @Field(name = "title",type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
    private String title;

    @Field(name = "note",type = FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_max_word")
    private String note;

    @Field(name = "url",type = FieldType.Text)
    private String url;

    @Field(name = "personal",type = FieldType.Integer)
    private Integer personal;

    @Field(name = "collecteddate",type = FieldType.Date,format = {DateFormat.date_optional_time,DateFormat.epoch_millis,DateFormat.date,DateFormat.year_month_day})
    private LocalDate collecteddate;

    @Field(name = "collectedtime",type = FieldType.Date,format = {DateFormat.hour_minute_second})
    private LocalTime collectedtime;

    @Field(name = "username",type = FieldType.Keyword)
    private String username;

    @Field(name = "avater",type = FieldType.Text)
    private String avater;

    @Field(name = "userid",type = FieldType.Long)
    private String userid;

}
