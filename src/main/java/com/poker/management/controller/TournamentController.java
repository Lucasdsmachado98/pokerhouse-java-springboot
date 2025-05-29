package com.poker.management.controller;

import com.poker.management.model.Tournament;
import com.poker.management.model.Player;
import com.poker.management.repository.PlayerRepository;
import com.poker.management.service.TournamentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final PlayerRepository playerRepository;
    
    public TournamentController(TournamentService tournamentService,
                                PlayerRepository playerRepository) {
        this.tournamentService = tournamentService;
        this.playerRepository   = playerRepository;
    }
    
    @GetMapping("/{id}")
    public Tournament getTournament(@PathVariable Long id) {
        return tournamentService.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tournament createTournament(@RequestBody Tournament tournament) {
        List<Player> incoming = tournament.getPlayers();
        if (incoming != null && !incoming.isEmpty()) {
            List<Player> attached = incoming.stream()
                    .map(p -> playerRepository.findById(p.getId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Player não encontrado com id: " + p.getId())))
                    .collect(Collectors.toList());
            tournament.setPlayers(attached);
        }
        return tournamentService.create(tournament);
    }
    
    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        tournament.setId(id);
        return tournamentService.update(tournament);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.delete(id);
    }
    
    @GetMapping("/{id}/players")
    public List<Player> getTournamentPlayers(@PathVariable Long id) {
        return tournamentService.findPlayersByTournamentId(id);
    }
    
    @PostMapping("/{tournamentId}/players/{playerId}")
    public Tournament addPlayerToTournament(@PathVariable Long tournamentId, @PathVariable Long playerId) {
        return tournamentService.addPlayerToTournament(tournamentId, playerId);
    }
    
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.findAll();
    }
}
