package com.kxj.WebPageCollect.repsitory.esrepsitory;

import com.kxj.WebPageCollect.entity.esentity.EsCollectEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.repsitory.esrepsitory
 * @Date 2022/9/19 23:37
 * @Version 1.0
 */
public interface EsCollectRepsitory extends ElasticsearchRepository<EsCollectEntity,Integer> {



}
