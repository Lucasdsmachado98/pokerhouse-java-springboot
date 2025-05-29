package com.poker.management.service;

import com.poker.management.model.Tournament;
import com.poker.management.model.Player;
import com.poker.management.repository.TournamentRepository;
import com.poker.management.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final PlayerRepository playerRepository;
    
    public TournamentService(TournamentRepository tournamentRepository, PlayerRepository playerRepository) {
        this.tournamentRepository = tournamentRepository;
        this.playerRepository = playerRepository;
    }
    
    public Tournament findById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Torneio não encontrado com ID: " + id));
    }
    
    @Transactional
    public Tournament create(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }
    
    @Transactional
    public Tournament update(Tournament tournament) {
        if (!tournamentRepository.existsById(tournament.getId())) {
            throw new EntityNotFoundException("Torneio não encontrado com ID: " + tournament.getId());
        }
        return tournamentRepository.save(tournament);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new EntityNotFoundException("Torneio não encontrado com ID: " + id);
        }
        tournamentRepository.deleteById(id);
    }
    
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }
    
    public List<Player> findPlayersByTournamentId(Long id) {
        Tournament tournament = findById(id);
        return tournament.getPlayers();
    }
    
    @Transactional
    public Tournament addPlayerToTournament(Long tournamentId, Long playerId) {
        Tournament tournament = findById(tournamentId);
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com ID: " + playerId));
        
        List<Player> players = tournament.getPlayers();
        
        // Verificar se o jogador já está inscrito e se reentradas são permitidas
        if (players.contains(player) && !tournament.getReentryAllowed()) {
            throw new IllegalStateException("Jogador já está inscrito e reentradas não são permitidas neste torneio");
        }
        
        players.add(player);
        tournament.setPlayers(players);
        
        return tournamentRepository.save(tournament);
    }
}
