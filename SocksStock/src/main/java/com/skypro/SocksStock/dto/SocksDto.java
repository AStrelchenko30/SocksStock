package com.skypro.SocksStock.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SocksDto {

private String color;
@Min(0)
@Max(100)
private int cottonPart;
@Min(1)
private int quantity;
}
