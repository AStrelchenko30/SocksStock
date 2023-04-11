package com.skypro.SocksStock.controller;

import com.skypro.SocksStock.dto.SocksDto;
import com.skypro.SocksStock.service.SocksStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/socks/")
public class SocksStockController {

    private SocksStockService socksStockService;

    @Operation(summary = "addSocksToStock",
            responses = {
                    @ApiResponse(responseCode = "200", description = "удалось добавить приход."),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат."),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны.")
            })
    @PostMapping(path = "/income")
    public ResponseEntity<SocksDto> addSocksToStock(@RequestBody SocksDto socksDto) {
        socksStockService.addSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "removeSocksFromStock",
            responses = {
                    @ApiResponse(responseCode = "200", description = "удалось удались со склада"),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")
            })
    @PostMapping(path = "/outcome")
    public ResponseEntity<Void> takeSocksFromStock(@RequestBody SocksDto socksDto) {
        socksStockService.removeSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Integer> getQuantityOfSocksBy(@RequestParam String color,
                                                        @RequestParam Integer cottonPart) {
        return ResponseEntity.ok(socksStockService.getQuantityOfSocksBy(color,cottonPart));
    }

}
