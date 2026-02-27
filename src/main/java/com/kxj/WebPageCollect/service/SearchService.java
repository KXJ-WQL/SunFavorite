package com.kxj.WebPageCollect.service;

import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.esentity.EsCollectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service
 * @Date 2022/9/19 23:55
 * @Version 1.0
 */

public interface SearchService {

    public List<CollectEntity> search(String keyword, Pageable pageable);

}
