package com.example.lodging_site.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "bfCategory")
@Entity
public class BfCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bfCategory;

    @OneToMany(mappedBy="bfCategory", cascade=CascadeType.ALL)
    private List<Lodging> lodging;

    // Default constructor
    public BfCategory() {
    }

    // Constructor with parameters
    public BfCategory(String bfCategory) {
        this.bfCategory = bfCategory;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBfCategory() {
        return bfCategory;
    }

    public void setBfCategory(String bfCategory) {
        this.bfCategory = bfCategory;
    }
}
