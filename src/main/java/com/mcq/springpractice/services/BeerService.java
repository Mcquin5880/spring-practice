package com.mcq.springpractice.services;

import com.mcq.springpractice.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Optional<Beer> getBeerById(UUID id);

    Beer createBeer(Beer beer);

    void updateBeerById(UUID id, Beer beer);

    void deleteBeerById(UUID id);
}
