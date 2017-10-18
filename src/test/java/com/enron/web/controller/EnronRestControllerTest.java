package com.enron.web.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.enron.web.AbstractIntegrationTest;
import com.enron.web.ApplicationTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@WebAppConfiguration
public class EnronRestControllerTest extends AbstractIntegrationTest
{

   private MockMvc mockMvc;

   @Autowired
   private WebApplicationContext webApplicationContext;

   @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;

   private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

   @Override
   public ElasticsearchTemplate getElasticsearchTemplate()
   {
      return elasticsearchTemplate;
   }

   @Before
   public void before()
   {
      super.setup();
      this.mockMvc = webAppContextSetup(webApplicationContext).build();
   }

   @Test
   public void executeWithSuccessGetEmailById() throws Exception
   {
      final String id = "2";
      mockMvc.perform(get("/api/email/" + id))
               .andExpect(status().isOk())
               .andExpect(content().contentType(contentType))
               .andExpect(jsonPath("$.id", is(id)))
               .andExpect(jsonPath("$.sender", is("charles.heidemann@gmail.com")));
   }

   @Test
   public void executeWithSuccesssearchEmailBySender() throws Exception
   {
      final String search = "{\"query\":\"(sender : *heidemann*)\"}";
      mockMvc.perform(post("/api/email/search?page=0&size=10").contentType(contentType).content(search))
               .andExpect(status().isOk())
               .andExpect(content().contentType(contentType))
               .andExpect(jsonPath("$.totalElements", is(2)))
               .andExpect(jsonPath("$.totalPages", is(1)))
               .andExpect(jsonPath("$.size", is(10)))
               .andExpect(jsonPath("$.number", is(0)));
   }
}