package com.kxj.WebPageCollect.entity;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.entity
 * @Date 2022/9/11 10:59
 * @Version 1.0
 */
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "openid")
    private String openid;

    @Column(name = "avater")
    private String avater;

    @Column(name = "createtime")
    LocalDateTime createtime;

    @Column(name = "lasttime")
    LocalDateTime lasttime;
}
