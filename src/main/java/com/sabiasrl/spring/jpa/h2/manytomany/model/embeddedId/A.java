package com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "A")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
public class A {
  public A(AKey aKey, String name) {
    this.aKey = aKey;
    this.name = name;
  }

  @EmbeddedId
  private AKey aKey;

  @Column(name = "name")
  private String name;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "aSet")
  private Set<B> bSet  = new HashSet<>();
}
