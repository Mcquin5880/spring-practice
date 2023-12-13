package com.mcq.springpractice.bootstrap;

import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.entities.Customer;
import com.mcq.springpractice.model.BeerCSVRecord;
import com.mcq.springpractice.model.BeerStyle;
import com.mcq.springpractice.repositories.BeerRepository;
import com.mcq.springpractice.repositories.CustomerRepository;
import com.mcq.springpractice.services.BeerCsvService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final BeerCsvService beerCsvService;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public void run(String... args) throws FileNotFoundException {
        generateBeerData();
        loadCsvData();
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

    private void loadCsvData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
        List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

        recs.forEach(beerCSVRecord -> {
            BeerStyle beerStyle = switch (beerCSVRecord.getStyle()) {
                case "American Pale Lager" -> BeerStyle.LAGER;
                case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" ->
                        BeerStyle.ALE;
                case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyle.IPA;
                case "American Porter" -> BeerStyle.PORTER;
                case "Oatmeal Stout", "American Stout" -> BeerStyle.STOUT;
                case "Saison / Farmhouse Ale" -> BeerStyle.SAISON;
                case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyle.WHEAT;
                case "English Pale Ale" -> BeerStyle.PALE_ALE;
                default -> BeerStyle.PILSNER;
            };

            beerRepository.save(Beer.builder()
                    .name(StringUtils.abbreviate(beerCSVRecord.getBeer(), 50))
                    .beerStyle(beerStyle)
                    .price(BigDecimal.TEN)
                    .upc(beerCSVRecord.getRow().toString())
                    .quantityOnHand(beerCSVRecord.getCount())
                    .build());
        });

    }

    private void generateCustomerData() {

        Customer cust1 = Customer.builder()
                .name("Michael")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer cust2 = Customer.builder()
                .name("John")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerRepository.save(cust1);
        customerRepository.save(cust2);
    }
}
