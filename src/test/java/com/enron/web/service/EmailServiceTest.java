package com.enron.web.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.enron.web.AbstractIntegrationTest;
import com.enron.web.ApplicationTest;
import com.enron.web.model.Email;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmailServiceTest extends AbstractIntegrationTest
{

   private static final Logger logger = LoggerFactory.getLogger(EmailServiceTest.class);

   @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;

   @Autowired
   private EmailService emailService;

   @Override
   public ElasticsearchTemplate getElasticsearchTemplate()
   {
      return elasticsearchTemplate;
   }

   @Before
   public void before()
   {
      super.setup();
   }

   @Test
   public void executeWithSuccessFindBySender()
   {
      final String sender = "charles.heidemann@gmail.com";
      final Page<Email> result = emailService.search("sender:\"" + sender + "\"", new PageRequest(0, 10));
      assertThat(result.getNumberOfElements(), is(equalTo(1)));
      assertThat(result.getTotalPages(), is(equalTo(1)));
      result.forEach(email -> {
         assertThat(email.getSender(), is(sender));
         logger.info(email.toString());
      });
   }

   @Test
   public void executeWithSuccessFindById()
   {
      final String id = "1";
      final Email email = emailService.findOne(id);
      assertThat(email.getId(), is(equalTo(id)));
      logger.info(email.toString());
   }
}