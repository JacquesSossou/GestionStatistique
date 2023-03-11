package com.rtr.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="etats")
@NoArgsConstructor
public class Etat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Etat(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    @NonNull
    @Column(name="label", unique = true)
    private String label;

    @OneToMany(mappedBy = "id.etatId")
    private Set<ReclamationEtat> reclamationEtats = new HashSet<ReclamationEtat>();
}
