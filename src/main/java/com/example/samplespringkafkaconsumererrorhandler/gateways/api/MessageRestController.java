package com.example.samplespringkafkaconsumererrorhandler.gateways.api;

import io.swagger.annotations.ApiOperation;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageRestController {

  private final KafkaTemplate<String, String> kafkaTemplate;
  @Value(value = "${spring.kafka.topics.simple-topic:topic-infinite-retry}")
  private String topicName;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Send a new message")
  public ResponseEntity<Long> sendNewMessage() throws ExecutionException, InterruptedException {
    SendResult<String, String> sendResult = kafkaTemplate.send(topicName, "messageBody").get();
    log.info("Message has been produced: offset {}", sendResult.getRecordMetadata().offset());
    return ResponseEntity.ok(sendResult.getRecordMetadata().offset());
  }
}
