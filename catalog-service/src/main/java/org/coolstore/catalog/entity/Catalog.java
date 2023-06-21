package org.coolstore.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Catalog {

    @Id
    public String itemId;
    public String title;
    public String category;
    @Column(name = "description", columnDefinition="TEXT")
    public String desc;
    public BigDecimal price;
    public String image;

}
