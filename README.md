# Enron Web Porjet #

## Technologies used in the project

* Oracle/Open JDK 1.8 (must be pre-installed).
* Maven 3.3+. (must be pre-installed).
* Elasticsearch 2.4.5 (must be pre-installed).
* [Spring Boot](https://projects.spring.io/spring-boot/) stand-alone Spring based applications.
* [Spring Data Elasticsearch](http://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/) Spring Data implementation for Elasticsearch.
* [JUnit](http://junit.org/junit4/) for unit testing.
* [Mockito](http://mockito.org/) for mocked testing.

## How to Run the Project using Maven

This project uses the [Maven](https://maven.apache.org/) build system, you can build and start the project locally. 

1. Go to `./code/enron_web` folder and typing the following command in the console:

```
mvn spring-boot:run -Drun.arguments="--elasticsearch.host=localhost, --elasticsearch.port=9300,--elasticsearch.clustername=elasticsearch" 

```

2. Go to http://localhost:9000

## How to Run the Project Tests

1. Go to `./code/enron_web` folder and typing the following command in the console:

```
mvn clean test 

```

## Assumptions:

1. The Enron Data Import have been executed previously and the Elasticsearch cluster remains
2. Elasticsearch transport TCP port is accessible