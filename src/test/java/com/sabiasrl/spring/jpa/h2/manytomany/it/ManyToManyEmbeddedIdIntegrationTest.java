package com.sabiasrl.spring.jpa.h2.manytomany.it;

import com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId.A;
import com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId.AKey;
import com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId.B;
import com.sabiasrl.spring.jpa.h2.manytomany.model.embeddedId.BKey;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class
ManyToManyEmbeddedIdIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Disabled
    @Test
    public void manyToManyTest() {
        var a11 = new A(new AKey("partition1", "sort1"), "a11");
        var b11 = new B(new BKey("partition1", "sort1"), "b11");

//        var a22 = new A(new AKey("partition2", "sort2"), "value22");
//
//        var b22 = new B(new BKey("partition2", "sort2"), "value22");

        entityManager.persist(a11);
        entityManager.persist(b11);
//        entityManager.persist(a22);
//        entityManager.persist(b22);

        b11.getASet().add(a11);

        Assertions.assertThat(entityManager.find(A.class, a11.getAKey())).isNotNull();
        Assertions.assertThat(entityManager.contains(a11)).isTrue();
        Assertions.assertThat(entityManager.contains(b11)).isTrue();

        entityManager.flush();
        entityManager.detach(b11);
        entityManager.detach(a11);
        var b11Refreshed = entityManager.find(B.class, b11.getBKey());

        Assertions.assertThat(b11Refreshed.getASet()).isNotEmpty();
        Assertions.assertThat(b11Refreshed.getASet()).hasSize(1);
        Assertions.assertThat(b11Refreshed.getASet()).containsExactly(a11);
        var a11Refreshed = b11Refreshed.getASet().stream().findFirst().orElse(null);
        Assertions.assertThat(a11Refreshed).isNotNull();
        Assertions.assertThat(a11Refreshed.getName()).isEqualTo("a11");

        Assertions.assertThat(a11Refreshed.getBSet()).isNotEmpty();
        Assertions.assertThat(a11Refreshed.getBSet()).hasSize(1);
        Assertions.assertThat(a11Refreshed.getBSet()).containsExactly(b11Refreshed);
    }

}
