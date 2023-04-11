package com.skypro.SocksStock.mapper;

import com.skypro.SocksStock.dto.SocksDto;
import com.skypro.SocksStock.entity.SocksStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocksMapper {

    SocksMapper INSTANCE =Mappers.getMapper(SocksMapper.class);

    @Mapping(target = "color", source = "color.name")
    SocksDto toDto(SocksStock socksStock);


    @Mapping(target = "color.name", source = "color")
    SocksStock toEntity(SocksDto socksDto);


}
