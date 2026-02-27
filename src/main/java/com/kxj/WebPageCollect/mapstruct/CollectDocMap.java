package com.kxj.WebPageCollect.mapstruct;

import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.esentity.EsCollectEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.mapstruct
 * @Date 2022/9/20 1:46
 * @Version 1.0
 */
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectDocMap {

    @Mappings({
            @Mapping(source = "userid", target = "user.id"),
            @Mapping(source = "avater", target = "user.avater"),
            @Mapping(source = "username", target = "user.username"),
    })
    CollectEntity toDto(EsCollectEntity collectDoc);

}
