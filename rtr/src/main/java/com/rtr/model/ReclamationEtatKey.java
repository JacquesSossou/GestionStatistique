package com.rtr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ReclamationEtatKey implements Serializable {


    @Column(name="reclamation_id")
    private Long reclamationId;


    @Column(name="etat_id")
    private Long etatId;


}
