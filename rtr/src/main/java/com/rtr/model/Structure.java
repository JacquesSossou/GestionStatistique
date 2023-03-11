package com.rtr.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="structures")
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nom;

    private String description;

    private String sigle;


    public Structure(@NonNull String nom, String description, String sigle) {
        this.nom = nom;
        this.description = description;
        this.sigle = sigle;
    }

    @OneToMany(mappedBy = "structure")
    private Set<Prestation> prestations;

    @OneToMany(mappedBy = "structure")
    private Set<Reclamation> reclamations;


}
