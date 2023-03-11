package com.rtr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="reclamations")
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name="nom")
    private String nom;

    @NonNull
    @Column(name="motif")
    private String motif;

    @NonNull
    @Column(name="prenoms")
    private String prenoms;

    @NonNull
    @Column(name="telephone")
    private String telephone;

    @NonNull
    @Column(name="email")
    private String email;

    @NonNull
    @Column(name="reference_dossier", unique = true)
    private String referenceDossier;

    @NonNull
    @Column(name="montant")
    private double montant;

    @Column(name="code_consultation",unique = true)
    private String codeConsultation;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "prestation_id")
    private Prestation prestation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "reclamation")
    private Set<Transaction> transactions;

   /* @ManyToOne
    @JoinColumn(name = "plaignant_id")
    private Plaignant plaignant;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure; */

    @OneToMany(mappedBy = "id.reclamationId")
    private Set<ReclamationEtat> reclamationEtats = new HashSet<ReclamationEtat>();

}
