package com.enron.web.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.enron.web.view.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Builder;
import lombok.Data;

/**
 * {@code Email} is annotated with @Document annotation and represents an Email Document on Elasticsearch
 */
@Data
@Builder
@Document(indexName = "enron", type = "email")
public class Email
{

   @Id
   @JsonView(Views.Basics.class)
   private String id;

   @JsonView(Views.Basics.class)
   private String sender;

   @JsonView(Views.Details.class)
   private List<String> to;

   @JsonView(Views.Details.class)
   private List<String> cc;

   @JsonView(Views.Details.class)
   private List<String> bcc;

   @JsonView(Views.Details.class)
   private List<String> recipients;

   @JsonView(Views.Details.class)
   private String text;

   @JsonView(Views.Details.class)
   private String mid;

   @JsonView(Views.Details.class)
   private String fpath;

   @JsonView(Views.Details.class)
   private String ctype;

   @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ssZ")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssXXX")
   @JsonView(Views.Details.class)
   private Date date;

}
