package com.example.ServiceCompany.repository;

import com.example.ServiceCompany.entity.ServiceCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCompanyRepository extends JpaRepository<ServiceCompanyEntity, String> {
}
