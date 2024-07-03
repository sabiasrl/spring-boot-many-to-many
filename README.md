# Spring Boot Many To Many example with Spring JPA, JPA, H2
The example proves the implementation of a many-to-many JPA relation using EmbeddedId as composite private keys.

## Run Spring Boot application
```
mvn clean install spring-boot:run
```

```java

    @Entity 
    public class A {

        @EmbeddedId
        private AKey aKey;
    
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(mappedBy = "aSet")
        private Set<B> bSet  = new HashSet<>();

```

```java

    @Entity
    public class B {

        @EmbeddedId
        private BKey bKey;
    
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
                joinColumns = {
                        @JoinColumn(name = "partitionKey"), 
                        @JoinColumn(name = "sortKey")},
                inverseJoinColumns = {
                        @JoinColumn(name = "bKey.partitionKey"),
                        @JoinColumn(name = "bKey.sortKey")}
        )
        private Set<A> aSet  = new HashSet<>();

```