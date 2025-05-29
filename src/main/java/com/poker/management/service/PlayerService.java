package com.poker.management.service;

import com.poker.management.model.Player;
import com.poker.management.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
    public Player findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com ID: " + id));
    }
    
    @Transactional
    public Player create(Player player) {
        return playerRepository.save(player);
    }
    
    @Transactional
    public Player update(Player player) {
        if (!playerRepository.existsById(player.getId())) {
            throw new EntityNotFoundException("Jogador não encontrado com ID: " + player.getId());
        }
        return playerRepository.save(player);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new EntityNotFoundException("Jogador não encontrado com ID: " + id);
        }
        playerRepository.deleteById(id);
    }
    
    public List<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }
    
    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
