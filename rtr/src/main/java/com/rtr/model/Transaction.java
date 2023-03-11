package com.rtr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name="numero")
    private String numero;

    @NonNull
    @Column(name="reference")
    private String reference;

    @NonNull
    @Column(name="full_name")
    private String fullName;

    @NonNull
    @Column(name="date")
    private String date;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "reclamation_id")
    private Reclamation reclamation;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "operateur_id")
    private Operateur operateur;


}
