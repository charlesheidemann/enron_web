package com.enron.web;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig
{

   @Bean
   public Jackson2ObjectMapperBuilderCustomizer setDefaultViewInclusion()
   {
      return new Jackson2ObjectMapperBuilderCustomizer()
      {
         @Override
         public void customize(final Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder)
         {
            jacksonObjectMapperBuilder.defaultViewInclusion(true);
         }
      };
   }

}