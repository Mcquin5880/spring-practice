package com.mcq.springpractice.bootstrap;

import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.entities.Customer;
import com.mcq.springpractice.model.BeerStyle;
import com.mcq.springpractice.repositories.BeerRepository;
import com.mcq.springpractice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        generateBeerData();
        generateCustomerData();
    }

    private void generateBeerData() {

        Beer beer1 = Beer.builder()
                .name("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .name("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .name("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();


        beerRepository.save(beer1);
        beerRepository.save(beer2);
        beerRepository.save(beer3);
    }

    private void generateCustomerData() {

        Customer cust1 = Customer.builder()
                .name("Michael")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer cust2 = Customer.builder()
                .name("John")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerRepository.save(cust1);
        customerRepository.save(cust2);
    }
}
