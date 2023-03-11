package com.rtr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="operateurs")
public class Operateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom", unique = true)
    private String nom;

    public Operateur(String nom) {
        this.nom = nom;
    }

    @OneToMany(mappedBy="operateur")
    private Set<Transaction> transactions;
}
