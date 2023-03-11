package com.rtr.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name="plaignants") // Not used yet
public class Plaignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;
    @Column(name="prenoms")
    private String prenoms;

    @Column(name="email")
    private String email;
    @Column(name="numero")
    private String numero;

    /*@OneToMany(mappedBy="plaignant")
    private Set<Reclammation> reclammations;*/
}
