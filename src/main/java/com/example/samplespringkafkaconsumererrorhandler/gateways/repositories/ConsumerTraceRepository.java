package com.example.samplespringkafkaconsumererrorhandler.gateways.repositories;

import com.example.samplespringkafkaconsumererrorhandler.domains.ConsumerTrace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConsumerTraceRepository
    extends MongoRepository<ConsumerTrace, String> {}
