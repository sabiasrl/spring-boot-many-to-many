package com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AKey implements Serializable {

  private String partitionKey;
  private String sortKey;

}
