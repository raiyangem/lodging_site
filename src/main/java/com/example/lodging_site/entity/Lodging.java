package com.example.lodging_site.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Table(name = "lodging")
@Entity
public class Lodging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lodgingName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bf_category_id")
    private BfCategory bfCategory;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalTime checkIn;

    @Column(nullable = false)
    private LocalTime checkOut;

    private String img;

    // Default constructor
    public Lodging() {
    }

    // Constructor with parameters
    public Lodging(String lodgingName, BfCategory bfCategory, String address, LocalTime checkIn, LocalTime checkOut, String img) {
        this.lodgingName = lodgingName;
        this.bfCategory = bfCategory;
        this.address = address;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.img = img;
    }

    public Lodging(Long id, String lodgingName, BfCategory bfCategory, String address, LocalTime checkIn, LocalTime checkOut, String img) {
        this.id = id;
        this.lodgingName = lodgingName;
        this.bfCategory = bfCategory;
        this.address = address;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.img = img;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLodgingName() {
        return lodgingName;
    }

    public void setLodgingName(String lodgingName) {
        this.lodgingName = lodgingName;
    }

    public BfCategory getBfCategory() {
        return bfCategory;
    }

    public void setBfCategory(BfCategory bfCategory) {
        this.bfCategory = bfCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}