package com.mcq.springpractice.repositories;

import com.mcq.springpractice.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerDataInit() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .name("Test Brew").build());

        assertThat(savedBeer.getId()).isNotNull();
    }
}
