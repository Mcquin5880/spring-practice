package com.mcq.springpractice.repositories;

import com.mcq.springpractice.bootstrap.InitData;
import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.model.BeerStyle;
import com.mcq.springpractice.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({InitData.class, BeerCsvServiceImpl.class})
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

    @Test
    void testGetBeerListByName() {
        List<Beer> list = beerRepository.findAllByNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);
    }
}
