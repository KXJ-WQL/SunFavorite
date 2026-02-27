package com.kxj.WebPageCollect.service.serviceimpl;

import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.esentity.EsCollectEntity;
import com.kxj.WebPageCollect.mapstruct.CollectDocMap;
import com.kxj.WebPageCollect.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service.serviceimpl
 * @Date 2022/9/19 23:59
 * @Version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    CollectDocMap collectDocMap;

    @Override
    public List<CollectEntity> search(String keyword, Pageable pageable) {
        SearchHits<EsCollectEntity> search;
        List<EsCollectEntity> EsCollectEntityCollect;
        Criteria criteria = new Criteria();

        criteria.and(new Criteria("personal").is(0))
                .and(new Criteria("title").matches(keyword));

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria).setPageable(pageable);

        search = elasticsearchRestTemplate.search(criteriaQuery, EsCollectEntity.class);

        EsCollectEntityCollect = search.get().map(e -> e.getContent()).collect(Collectors.toList());

        if(EsCollectEntityCollect.size()==0){
            Criteria criteria1 = new Criteria();

            criteria1.and(new Criteria("personal").is(0))
                    .and(new Criteria("note").matches(keyword));
            CriteriaQuery criteriaQuery1 = new CriteriaQuery(criteria1).setPageable(pageable);
            search = elasticsearchRestTemplate.search(criteriaQuery1, EsCollectEntity.class);
            EsCollectEntityCollect = search.get().map(e -> e.getContent()).collect(Collectors.toList());
        }
        Page<EsCollectEntity> docPage = new PageImpl(EsCollectEntityCollect, pageable, search.getTotalHits());

        return  docPage.map(collectDocMap::toDto).getContent();
    }
}