package com.kxj.WebPageCollect.ATO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.ATO
 * @Date 2022/9/14 19:18
 * @Version 1.0
 */
@Data
public class CollectAto implements Serializable {

    private String title;

    private List<CollectAto> collectAtoes = new ArrayList<>();

}
