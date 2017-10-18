package com.enron.web;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import com.enron.web.model.Email;

public abstract class AbstractIntegrationTest
{

   public abstract ElasticsearchTemplate getElasticsearchTemplate();

   private IndexQuery buildIndex(String id, Object object)
   {
      IndexQuery indexQuery = new IndexQuery();
      indexQuery.setId(id);
      indexQuery.setObject(object);
      return indexQuery;
   }

   public void setup()
   {
      getElasticsearchTemplate().createIndex(Email.class);
      getElasticsearchTemplate().putMapping(Email.class);
      final Email email1 = Email.builder()
               .sender("c.heidemann@tj.rs.gov.br")
               .text("Hi Michael -\n\nMr. Lay is returning from his trip today.  He will be in the office tomorrow.\n\nRosalee\n")
               .build();
      final Email email2 = Email.builder()
               .sender("charles.heidemann@gmail.com")
               .text("Hi Mr. Yergin -\n\nI just wanted to make sure that you knew that Ken does plan to do this.  He \ndid talk to Governor Knowles.\n\nThanks.\n\nRosalee\n")
               .build();
      getElasticsearchTemplate().index(buildIndex("1", email1));
      getElasticsearchTemplate().index(buildIndex("2", email2));

   }

}
