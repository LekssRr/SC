package com.example.ServiceCompany.repository;

import com.example.ServiceCompany.entity.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<AutoEntity, String> {
}
