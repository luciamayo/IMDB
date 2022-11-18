package co.empathy.academy.searchmyexample.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.empathy.academy.searchmyexample.service.ElasticEngine;
import co.empathy.academy.searchmyexample.service.ElasticService;
import org.springframework.context.annotation.Bean;
import co.empathy.academy.searchmyexample.service.ElasticServiceImp;
import co.empathy.academy.searchmyexample.service.ElasticEngineImp;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramConfig {


    @Bean
    public ElasticEngine elasticEngine(ElasticsearchClient elasticClient) {
        return new ElasticEngineImp(elasticClient);
    }

    @Bean
    public ElasticService elasticService(ElasticEngine elasticEngine) {
        return new ElasticServiceImp(elasticEngine);
    }

}
