package com.enron.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
public class Application implements CommandLineRunner
{

   @Override
   public void run(String... args) throws Exception
   {
   }

   public static void main(String[] args)
   {
      SpringApplication.run(Application.class, args);
   }

}