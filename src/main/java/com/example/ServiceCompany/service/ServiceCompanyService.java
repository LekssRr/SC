package com.example.ServiceCompany.service;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.dto.ServiceCompanyDto;

import java.util.List;

public interface ServiceCompanyService {
    List<ServiceCompanyDto> getAllServiceCompany();
    List<AutoDto> getAllVinToServiceCompany(String nameServiceCompany);
    Boolean addServiceCompany(String nameServiceCompany);
    Boolean deleteServiceCompany(String nameServiceCompany);
    Boolean deleteAllServiceCompany();
    Boolean updateServiceCompany(String oldSc, String newSC);
}
