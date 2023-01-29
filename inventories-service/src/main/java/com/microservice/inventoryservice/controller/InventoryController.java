package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory/iphone-13,iphone13-red

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @SneakyThrows
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Wait started!");
        Thread.sleep(10000);
        log.info("Wait ended!");
        return inventoryService.isInStock(skuCode);
    }
}
