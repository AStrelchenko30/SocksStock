package com.skypro.SocksStock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughAmountOfSocksInStockException extends RuntimeException{
}
