package com.enron.web;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.enron.web.repository")
public class ElasticsearchConfig
{

   @Value("${elasticsearch.host}")
   private String elasticsearchHost;

   @Value("${elasticsearch.port}")
   private int elasticsearchPort;

   @Value("${elasticsearch.clustername}")
   private String elasticsearchClusterName;

   @Bean
   public Client client() throws Exception
   {
      Settings elasticsearchSettings = Settings.settingsBuilder().put("cluster.name", elasticsearchClusterName).build();

      return TransportClient.builder()
               .settings(elasticsearchSettings)
               .build()
               .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName(elasticsearchHost), elasticsearchPort));
   }

   @Bean
   public ElasticsearchOperations elasticsearchTemplate() throws Exception
   {
      return new ElasticsearchTemplate(client());
   }
}
