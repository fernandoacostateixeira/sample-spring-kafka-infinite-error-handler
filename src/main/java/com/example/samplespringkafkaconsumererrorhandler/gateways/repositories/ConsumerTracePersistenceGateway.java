package com.example.samplespringkafkaconsumererrorhandler.gateways.repositories;

import com.example.samplespringkafkaconsumererrorhandler.domains.ConsumerTrace;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerTracePersistenceGateway {

  private final ConsumerTraceRepository repository;

  public void execute(final ConsumerRecord<String, String> consumerRecord) {
    repository
        .findById(buildIdentifier(consumerRecord))
        .ifPresentOrElse(this::updateDocument, () -> insertDocument(consumerRecord));
  }

  private void insertDocument(final ConsumerRecord<String, String> consumerRecord) {
    ConsumerTrace consumerTrace = new ConsumerTrace();
    consumerTrace.setId(buildIdentifier(consumerRecord));
    consumerTrace.setOffset(consumerRecord.offset());
    consumerTrace.setTopicName(consumerRecord.topic());
    consumerTrace.setPartition(consumerRecord.partition());
    consumerTrace.setCreateDate(LocalDateTime.now());
    consumerTrace.setLastUpdateDate(LocalDateTime.now());
    repository.insert(consumerTrace);
  }

  private void updateDocument(final ConsumerTrace consumerTrace) {
    final AtomicInteger atomicInteger = new AtomicInteger(consumerTrace.getConsumerQuantity());
    consumerTrace.setConsumerQuantity(atomicInteger.incrementAndGet());
    consumerTrace.setLastUpdateDate(LocalDateTime.now());
    repository.save(consumerTrace);
  }

  private String buildIdentifier(final ConsumerRecord<String, String> consumerRecord) {
    return consumerRecord.topic().concat("-").concat(String.valueOf(consumerRecord.offset()));
  }
}
