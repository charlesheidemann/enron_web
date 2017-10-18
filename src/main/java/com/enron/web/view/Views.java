package com.enron.web.view;

/**
 * These interfaces is used to configure json serializer
 */
public class Views
{

   /**
    * Basic view with reduced properties
    */
   public interface Basics
   {
   }

   /**
    * complete view with all properties
    */
   public interface Details extends Basics
   {
   }

}