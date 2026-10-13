package com.sanj.projectcatalogueservice.model;

import com.sanj.projectcatalogueservice.model.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {
    private int id;
    private Date createdAt;
    private Date lasUpdatedAt;
    private State state; // ACTIVE, INACTIVE
}
