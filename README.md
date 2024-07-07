# Spring Boot Many To Many example with Spring JPA, JPA, H2
The example proves the implementation of a many-to-many JPA relation using EmbeddedId as composite private keys.

## JPA Mapping
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

## Integration test
see [ManyToManyEmbeddedIdIntegrationTest](https://github.com/sabiasrl/spring-boot-many-to-many/blob/master/src/test/java/com/sabiasrl/spring/jpa/h2/manytomany/integration_tests/ManyToManyEmbeddedIdIntegrationTest.java)


## Validate and Run Spring Boot application
```shell
mvn clean verify

mvn clean install spring-boot:run
```