package com.skypro.SocksStock.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SocksDto {
    @NotBlank
    private String color;
    @Min(0)
    @Max(100)
    private int cottonPart;
    @Min(1)
    private int quantity;
}
