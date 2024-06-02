package com.example.lodging_site.repository;

import com.example.lodging_site.entity.BfCategory;
import com.example.lodging_site.entity.Lodging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {
    List<Lodging> findAllByBfCategory(BfCategory bfCategory);
}