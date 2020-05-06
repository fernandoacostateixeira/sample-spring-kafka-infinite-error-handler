package com.example.samplespringkafkaconsumererrorhandler.gateways.kafka;

import com.example.samplespringkafkaconsumererrorhandler.gateways.repositories.ConsumerTracePersistenceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleKafkaListener {

  private final ConsumerTracePersistenceGateway consumerTracePersistenceGateway;

  @KafkaListener(topics = "${spring.kafka.topics.simple-topic:topic-infinite-retry}")
  public void execute(final ConsumerRecord<String, String> consumerRecord) {
    consumerTracePersistenceGateway.execute(consumerRecord);
    throw new RuntimeException("Oops!");
  }
}
