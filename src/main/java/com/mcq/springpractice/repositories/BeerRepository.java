package com.mcq.springpractice.repositories;

import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.model.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    List<Beer> findAllByNameIsLikeIgnoreCase(String beerName);

    List<Beer> findAllByBeerStyle(BeerStyle beerStyle);

    List<Beer> findAllByNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle);
}
