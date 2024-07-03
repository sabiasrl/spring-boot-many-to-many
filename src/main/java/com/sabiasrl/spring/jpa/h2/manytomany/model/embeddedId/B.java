package com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "B")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Entity
public class B {

  @EmbeddedId
  private BKey bKey;

  public B(BKey bKey, String name) {
    this.bKey = bKey;
    this.name = name;
  }

  @Column(name = "name")
  private String name;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE,
          CascadeType.DETACH,
          CascadeType.REFRESH
  })
  @MapsId("aKey")
  @JoinTable(name = "AB_JOIN_TABLE",
          joinColumns = {@JoinColumn(name = "partitionKey"), @JoinColumn(name = "sortKey")},
          inverseJoinColumns = {@JoinColumn(name = "bKey.partitionKey"), @JoinColumn(name = "bKey.sortKey")}
  )
  private Set<A> aSet  = new HashSet<>();
}
