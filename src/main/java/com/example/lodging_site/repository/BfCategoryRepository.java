package com.example.lodging_site.repository;

import com.example.lodging_site.entity.BfCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BfCategoryRepository extends JpaRepository<BfCategory, Long> {
}