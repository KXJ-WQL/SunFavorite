package com.kxj.WebPageCollect.repsitory;

import com.kxj.WebPageCollect.entity.CollectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.repsitory
 * @Date 2022/9/14 0:07
 * @Version 1.0
 */
@Repository
public interface collectRepsitory extends CrudRepository<CollectEntity,Integer>, JpaSpecificationExecutor<CollectEntity> {

    @Query(value = "SELECT DISTINCT collecteddate FROM collect WHERE userid=?1 GROUP BY collecteddate ORDER BY collecteddate DESC",nativeQuery = true)
    List<Date> getDatelineByCollecteddateID(Integer userid);


    Page<CollectEntity> findAllByPersonal(Integer personal, Pageable pageable);

}
