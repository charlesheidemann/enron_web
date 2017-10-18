package com.enron.web;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@ComponentScan
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class ApplicationTest
{

   @Test
   public void contextLoads() throws Exception
   {
   }

   @Bean
   public Client client() throws Exception
   {
      Settings elasticsearchSettings = Settings.settingsBuilder()
               .put("http.enabled", "false")
               .put("path.data", "target/elasticsearch-data")
               .put("path.home", "src/test/resources/test-home-dir")
               .build();

      return nodeBuilder().local(true).settings(elasticsearchSettings).node().client();
   }

   @Bean
   public ElasticsearchOperations elasticsearchTemplate() throws Exception
   {
      return new ElasticsearchTemplate(client());
   }
}