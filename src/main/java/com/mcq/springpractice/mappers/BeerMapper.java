package com.mcq.springpractice.mappers;

import com.mcq.springpractice.entities.Beer;
import com.mcq.springpractice.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
