package com.enron.web.controller;

import java.lang.reflect.Field;

import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.InternalAggregations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enron.web.dto.QueryDTO;
import com.enron.web.model.Email;
import com.enron.web.service.EmailService;
import com.enron.web.view.Views;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/email")
public class EnronRestController
{

   private final EmailService emailService;

   @Autowired
   public EnronRestController(final EmailService emailService)
   {
      this.emailService = emailService;
   }

   /**
    * Get the email for a given _id.
    *
    * @param _id _id to search
    * @return the email for the given _id
    */
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   @JsonView(Views.Details.class)
   public Email getEmailbyId(@PathVariable("id") String id)
   {
      return emailService.findOne(id);
   }

   /**
    * Search by Emails in Elastisearch with the given query.
    *
    * @param query Elasticsearch query format Ex: (sender:rosalee) AND (to:"rebekah.rushing@enron.com")
    * @return the Page<Email> for the query result
    */
   @RequestMapping(value = "/search", method = RequestMethod.POST)
   @JsonView(Views.Basics.class)
   public Page<Email> search(@RequestBody QueryDTO query, Pageable pageable)
   {
      return removeNull(emailService.search(query.getQuery(), pageable));
   }

   /**
    * DATAES-274 prevent NPE in FacetedPageImpl #175
    * 
    * @see https://github.com/spring-projects/spring-data-elasticsearch/pull/175
    */
   private Page<Email> removeNull(Page<Email> result)
   {
      AggregatedPageImpl<Email> aggregatedPage = (AggregatedPageImpl<Email>) result;
      Aggregations aggregations = aggregatedPage.getAggregations();
      if (aggregations == null)
      {
         Field field = ReflectionUtils.findField(AggregatedPageImpl.class, "aggregations");
         ReflectionUtils.makeAccessible(field);
         ReflectionUtils.setField(field, aggregatedPage, InternalAggregations.EMPTY);
         return aggregatedPage;
      }
      return result;
   }

}