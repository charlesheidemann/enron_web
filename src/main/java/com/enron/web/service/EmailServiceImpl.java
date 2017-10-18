package com.enron.web.service;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enron.web.model.Email;
import com.enron.web.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService
{

   private final EmailRepository emailRepository;

   @Autowired
   public EmailServiceImpl(final EmailRepository emailRepository)
   {
      this.emailRepository = emailRepository;
   }

   @Override
   public Email findOne(String id)
   {
      return emailRepository.findOne(id);

   }

   @Override
   public Page<Email> search(String query, Pageable pageable)
   {
      return emailRepository.search(new QueryStringQueryBuilder(query), pageable);
   }

}