package com.enron.web.dto;

import lombok.Data;

/**
 * This Data Transfer Object (DTO) is used to receive an Elasticsearch query from the client.
 */
@Data
public class QueryDTO
{

   private String query;

}