package com.enron.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enron.web.model.Email;

public interface EmailService
{

   public Email findOne(String id);

   public Page<Email> search(String query, Pageable pageable);

}