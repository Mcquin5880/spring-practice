package com.mcq.springpractice.controller;

import com.mcq.springpractice.exception.NotFoundException;
import com.mcq.springpractice.model.BeerDTO;
import com.mcq.springpractice.model.BeerStyle;
import com.mcq.springpractice.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public List<BeerDTO> listBeers(@RequestParam(required = false) String beerName,
                                   @RequestParam(required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false) Boolean showInventory) {
        return beerService.listBeers(beerName, beerStyle, showInventory);
    }

    @GetMapping("/{id}")
    public BeerDTO getBeerById(@PathVariable UUID id) {
        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity createBeer(@Validated @RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.createBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBeerById(@PathVariable UUID id, @Validated @RequestBody BeerDTO beer) {
        if (beerService.updateBeerById(id, beer).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBeerById(@PathVariable UUID id) {
        if (!beerService.deleteBeerById(id)) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
