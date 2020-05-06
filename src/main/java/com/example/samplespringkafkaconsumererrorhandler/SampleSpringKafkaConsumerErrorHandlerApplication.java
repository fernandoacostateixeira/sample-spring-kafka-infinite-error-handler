package com.example.samplespringkafkaconsumererrorhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class SampleSpringKafkaConsumerErrorHandlerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SampleSpringKafkaConsumerErrorHandlerApplication.class, args);
  }
}
