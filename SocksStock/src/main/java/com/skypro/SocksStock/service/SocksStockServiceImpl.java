package com.skypro.SocksStock.service;

import com.skypro.SocksStock.Repository.ColorRepository;
import com.skypro.SocksStock.Repository.SocksStockRepository;
import com.skypro.SocksStock.dto.SocksDto;
import com.skypro.SocksStock.entity.Color;
import com.skypro.SocksStock.entity.SocksStock;
import com.skypro.SocksStock.exceptions.NoSuchColorException;
import com.skypro.SocksStock.exceptions.NoSuchSocksInStockException;
import com.skypro.SocksStock.exceptions.NotEnoughAmountOfSocksInStockException;
import com.skypro.SocksStock.mapper.SocksMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocksStockServiceImpl implements SocksStockService {

    private final SocksStockRepository socksStockRepository;
    private final ColorRepository colorRepository;
    private final SocksMapper socksMapper;

    @Override
    public int getQuantityOfSocksBy(String color, Integer cottonPart) {
        Optional<SocksStock> socksStocks = socksStockRepository.findByColorNameAndCottonPart(color, cottonPart);
        List<SocksStock> socksStocksList = socksStocks.stream().toList();
        return socksStocksList.size() + 1;
    }

    @Override
    public void addSocks(SocksDto socksStockDto) {
        Color currentColor = colorRepository.findByName(socksStockDto.getColor())
                .orElseGet(() -> createColor(socksStockDto));
        Optional<SocksStock> stockOptional = socksStockRepository
                .findByColorIdAndCottonPart(currentColor.getId(), socksStockDto.getCottonPart());
        SocksStock newSocksStock;
        if (stockOptional.isPresent()) {
            newSocksStock = stockOptional.get();
            newSocksStock.setQuantity(newSocksStock.getQuantity() + socksStockDto.getQuantity());
        } else {
            newSocksStock = socksMapper.toEntity(socksStockDto);
            newSocksStock.setColor(currentColor);
        }

        socksStockRepository.save(newSocksStock);
    }

    @Override
    public void removeSocks(SocksDto socksStockDto) {
        Color currentColor = colorRepository.findByName(socksStockDto.getColor())
                .orElseThrow(NoSuchColorException::new);
        SocksStock socksStock = socksStockRepository.findByColorIdAndCottonPart(currentColor.getId(), socksStockDto.getCottonPart())
                .orElseThrow(NoSuchSocksInStockException::new);
        if (socksStock.getQuantity() < socksStockDto.getQuantity()) {
            throw new NotEnoughAmountOfSocksInStockException();
        }
        socksStock.setQuantity(socksStock.getQuantity() - socksStockDto.getQuantity());
        socksStockRepository.save(socksStock);

    }

    private Color createColor(SocksDto socksDto) {
        Color currentColor = new Color();
        currentColor.setName(socksDto.getColor());
        return colorRepository.save(currentColor);
    }

}
