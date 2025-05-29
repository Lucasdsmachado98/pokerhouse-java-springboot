package com.poker.management.controller;

import com.poker.management.model.Dealer;
import com.poker.management.service.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dealer")
public class DealerController {
    private final DealerService dealerService;
    
    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }
    
    @GetMapping("/{id}")
    public Dealer getDealer(@PathVariable Long id) {
        return dealerService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dealer createDealer(@RequestBody Dealer dealer) {
        return dealerService.create(dealer);
    }
    
    @PutMapping("/{id}")
    public Dealer updateDealer(@PathVariable Long id, @RequestBody Dealer dealer) {
        dealer.setId(id);
        return dealerService.update(dealer);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDealer(@PathVariable Long id) {
        dealerService.delete(id);
    }
    
    @GetMapping
    public List<Dealer> getAllDealers() {
        return dealerService.findAll();
    }
}
