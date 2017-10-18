package com.enron.web.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.enron.web.model.Email;

public interface EmailRepository extends ElasticsearchRepository<Email, String>
{
}