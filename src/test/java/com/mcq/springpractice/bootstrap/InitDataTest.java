package com.mcq.springpractice.bootstrap;

import com.mcq.springpractice.repositories.BeerRepository;
import com.mcq.springpractice.repositories.CustomerRepository;
import com.mcq.springpractice.services.BeerCsvService;
import com.mcq.springpractice.services.BeerCsvServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BeerCsvServiceImpl.class)
class InitDataTest {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerCsvService csvService;

    InitData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new InitData(beerRepository, csvService, customerRepository);
    }

    @Test
    void Testrun() throws Exception {
        bootstrapData.run(null);

        assertThat(beerRepository.count()).isEqualTo(2413);
        assertThat(customerRepository.count()).isEqualTo(2);
    }
}
