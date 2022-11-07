package de.iplytics.codingchallenge_backend_webapp.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrClientConfig {
    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder()
                .withBaseSolrUrl("http://localhost:8983/solr")
                .build();
    }
}
