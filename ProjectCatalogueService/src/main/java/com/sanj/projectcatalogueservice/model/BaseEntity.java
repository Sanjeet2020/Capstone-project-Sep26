package com.sanj.projectcatalogueservice.model;

import com.sanj.projectcatalogueservice.model.enums.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdAt;
    private Date lasUpdatedAt;
    @Enumerated(EnumType.STRING)
    private State state; // ACTIVE, INACTIVE
}
