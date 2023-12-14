package com.mcq.springpractice.services;

import com.mcq.springpractice.model.BeerDTO;
import com.mcq.springpractice.model.BeerStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO createBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer);

    Boolean deleteBeerById(UUID id);
}
