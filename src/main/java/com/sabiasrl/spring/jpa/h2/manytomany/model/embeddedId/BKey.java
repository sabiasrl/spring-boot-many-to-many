package com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BKey implements Serializable {
  
  private String partitionKey;
  private String sortKey;

}
