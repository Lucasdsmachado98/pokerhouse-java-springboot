package com.poker.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Tournament {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 255)
    private String name;
    
    @NotNull
    private Double entryFee;
    
    @NotNull
    private Double prizePool;
    
    @NotNull
    private Boolean reentryAllowed;
    
    @ManyToMany
    @JoinTable(
        name = "tournament_player",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;
    
    // Construtores
    public Tournament() {
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getEntryFee() {
        return entryFee;
    }
    
    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }
    
    public Double getPrizePool() {
        return prizePool;
    }
    
    public void setPrizePool(Double prizePool) {
        this.prizePool = prizePool;
    }
    
    public Boolean getReentryAllowed() {
        return reentryAllowed;
    }
    
    public void setReentryAllowed(Boolean reentryAllowed) {
        this.reentryAllowed = reentryAllowed;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
