package com.kxj.WebPageCollect.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.config
 * @Date 2022/9/19 22:37
 * @Version 1.0
 */
@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Value("${ES.host}")
    String host;

    @Value("${ES.port}")
    String port;

    @Override
    public RestHighLevelClient elasticsearchClient() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(host + ":" + port)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(){
        ElasticsearchRestTemplate elasticsearchRestTemplate = new ElasticsearchRestTemplate(elasticsearchClient());
        return elasticsearchRestTemplate;
    }

}
