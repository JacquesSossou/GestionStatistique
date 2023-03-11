package com.rtr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="prestations")
public class Prestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String libelle;

    private double montant;

    public Prestation(String libelle, double montant, Structure structure) {
        this.libelle = libelle;
        this.montant = montant;
        this.structure = structure;
    }

    @ManyToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @OneToMany(mappedBy = "prestation")
    private Set<Reclamation> reclamations;
}
