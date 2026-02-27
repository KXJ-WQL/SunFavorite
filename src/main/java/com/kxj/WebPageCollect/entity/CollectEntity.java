package com.kxj.WebPageCollect.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.entity
 * @Date 2022/9/13 22:33
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "collect")
public class CollectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "note")
    private String note;

    @Column(name = "personal")
    private Integer personal=0;

    @Column(name = "collecteddate")
    private LocalDate collecteddate;

    @Column(name = "collectedtime")
    private LocalTime collectedtime;

}
