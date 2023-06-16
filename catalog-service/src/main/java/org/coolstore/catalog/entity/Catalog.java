package org.coolstore.catalog.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Catalog {

    @Id
    public String itemId;
    public String name;

    @Column(name = "description")
    public String desc;
    public BigDecimal price;
}
