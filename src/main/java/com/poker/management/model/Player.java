package com.poker.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 255)
    private String name;
    
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    @NotNull
    private Double chips;
    
    @NotNull
    private Integer pokerChips;
    
    @ManyToMany(mappedBy = "players")
    private List<Tournament> tournaments;
    
    // Construtores
    public Player() {
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Double getChips() {
        return chips;
    }
    
    public void setChips(Double chips) {
        this.chips = chips;
    }
    
    public Integer getPokerChips() {
        return pokerChips;
    }
    
    public void setPokerChips(Integer pokerChips) {
        this.pokerChips = pokerChips;
    }

    @ManyToMany(mappedBy = "players")
    @JsonIgnore
    public List<Tournament> getTournaments() {
        return tournaments;
    }
    
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
