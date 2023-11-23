package com.mcq.springpractice.bootstrap;

import com.mcq.springpractice.repositories.BeerRepository;
import com.mcq.springpractice.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InitDataTest {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    InitData initData;

    @BeforeEach
    void setUp() {
        initData = new InitData(beerRepository, customerRepository);
    }

    @Test
    void run() {
        initData.run(null);
        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(2);
    }
}
