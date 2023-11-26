package com.mcq.springpractice.repositories;

import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testBeerDataInit() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .name("Test Brew")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("9.99")).build());

        beerRepository.flush();

        assertThat(savedBeer.getId()).isNotNull();
    }

    @Test
    void testBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            final String wowThisNameIsTooLong = "Test Brew Too Looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong";

            beerRepository.save(Beer.builder()
                    .name(wowThisNameIsTooLong)
                    .beerStyle(BeerStyle.IPA)
                    .upc("12356")
                    .price(new BigDecimal("9.99")).build());

            beerRepository.flush();
        });
    }
}
