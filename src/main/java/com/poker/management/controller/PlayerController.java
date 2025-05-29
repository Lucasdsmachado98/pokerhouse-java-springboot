package com.poker.management.controller;

import com.poker.management.model.Player;
import com.poker.management.model.Tournament;
import com.poker.management.repository.PlayerRepository;
import com.poker.management.repository.TournamentRepository;
import com.poker.management.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;

    public PlayerController(PlayerService playerService, PlayerRepository playerRepository,
                            TournamentRepository tournamentRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
    }
    
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return playerService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody Player player) {
        return playerService.create(player);
    }
    
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        player.setId(id);
        return playerService.update(player);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deletePlayer(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player não encontrado com id: " + id));
        for (Tournament t : new ArrayList<>(player.getTournaments())) {
            t.getPlayers().remove(player);
            tournamentRepository.save(t);      // salva cada torneio sem o player
        }
        playerRepository.deleteById(id);
    }
    
    @GetMapping
    public List<Player> getAllPlayers(@RequestParam(required = false) String name) {
        if (name != null) {
            return playerService.findByName(name);
        }
        return playerService.findAll();
    }
}
