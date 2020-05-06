package com.example.samplespringkafkaconsumererrorhandler.domains;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "offset")
public class ConsumerTrace {

  @Id private String id;
  private Long offset;
  private Integer partition;
  private String topicName;
  private Integer consumerQuantity = 1;
  private Boolean ack;

  private LocalDateTime createDate;
  private LocalDateTime lastUpdateDate;
}
