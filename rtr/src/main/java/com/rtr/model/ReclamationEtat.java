package com.rtr.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="reclamation_etats")
public class ReclamationEtat {
    @EmbeddedId
    ReclamationEtatKey id;

    @NonNull
    @ManyToOne
    @MapsId("reclamationId")
    @JoinColumn(name = "reclamation_id")
    Reclamation reclamation;

    @NonNull
    @ManyToOne
    @MapsId("etatId")
    @JoinColumn(name = "etat_id")
    Etat etat;

    @NonNull
    @Column(name="date_time")
    LocalDateTime datetime;
}
