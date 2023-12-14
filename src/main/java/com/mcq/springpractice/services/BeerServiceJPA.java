package com.mcq.springpractice.services;

import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.mappers.BeerMapper;
import com.mcq.springpractice.model.BeerDTO;
import com.mcq.springpractice.model.BeerStyle;
import com.mcq.springpractice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory) {

        List<Beer> beerList;

        if (StringUtils.hasText(beerName) && beerStyle == null) {
            beerList = listBeersByName(beerName);
        } else if (!StringUtils.hasText(beerName) && beerStyle != null) {
            beerList = listBeersByStyle(beerStyle);
        } else if (StringUtils.hasText(beerName) && beerStyle != null) {
            beerList = listBeersByNameAndStyle(beerName, beerStyle);
        } else {
            beerList = beerRepository.findAll();
        }

        if (showInventory != null && !showInventory) {
            beerList.forEach(beer -> beer.setQuantityOnHand(null));
        }

        return beerList.stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public BeerDTO createBeer(BeerDTO beer) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(id).ifPresentOrElse(foundBeer -> {
            foundBeer.setName(beer.getName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            foundBeer.setUpc(beer.getUpc());
            foundBeer.setPrice(beer.getPrice());
            atomicReference.set(Optional.of(beerMapper.beerToBeerDto(beerRepository.save(foundBeer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public Boolean deleteBeerById(UUID id) {
        if (beerRepository.existsById(id)) {
            beerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Beer> listBeersByName(String beerName) {
        return beerRepository.findAllByNameIsLikeIgnoreCase("%" + beerName + "%");
    }

    public List<Beer> listBeersByStyle(BeerStyle beerStyle) {
        return beerRepository.findAllByBeerStyle(beerStyle);
    }

    public List<Beer> listBeersByNameAndStyle(String beerName, BeerStyle beerStyle) {
        return beerRepository.findAllByNameIsLikeIgnoreCaseAndBeerStyle("%" + beerName + "%", beerStyle);
    }
}
