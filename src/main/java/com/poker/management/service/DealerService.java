package com.poker.management.service;

import com.poker.management.model.Dealer;
import com.poker.management.repository.DealerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealerService {
    private final DealerRepository dealerRepository;
    
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }
    
    public Dealer findById(Long id) {
        return dealerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dealer não encontrado com ID: " + id));
    }
    
    @Transactional
    public Dealer create(Dealer dealer) {
        return dealerRepository.save(dealer);
    }
    
    @Transactional
    public Dealer update(Dealer dealer) {
        if (!dealerRepository.existsById(dealer.getId())) {
            throw new EntityNotFoundException("Dealer não encontrado com ID: " + dealer.getId());
        }
        return dealerRepository.save(dealer);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!dealerRepository.existsById(id)) {
            throw new EntityNotFoundException("Dealer não encontrado com ID: " + id);
        }
        dealerRepository.deleteById(id);
    }
    
    public List<Dealer> findAll() {
        return dealerRepository.findAll();
    }
}
