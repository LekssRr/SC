package com.example.ServiceCompany.service;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.dto.ServiceCompanyDto;
import com.example.ServiceCompany.entity.AutoEntity;
import com.example.ServiceCompany.entity.ServiceCompanyEntity;
import com.example.ServiceCompany.repository.AutoRepository;
import com.example.ServiceCompany.repository.ServiceCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceCompanyServiceImpl implements ServiceCompanyService {

    private final ServiceCompanyRepository serviceCompanyRepository;
    private final AutoRepository autoRepository;

    public ServiceCompanyServiceImpl(ServiceCompanyRepository newServiceCompanyRepository, AutoRepository newAutoRepository) {
        this.serviceCompanyRepository = newServiceCompanyRepository;
        this.autoRepository = newAutoRepository;
    }
    @Override
    public List<ServiceCompanyDto> getAllServiceCompany() {
        List<ServiceCompanyEntity> serviceCompanyEntities = serviceCompanyRepository.findAll();
        List<ServiceCompanyDto> result = new ArrayList<>();
        for (int i = 0; i <= serviceCompanyEntities.size() - 1; i++) {
            result.add(new ServiceCompanyDto(serviceCompanyEntities.get(i).getNameServiceCompany()));
        }
        return result;

    }
    @Override
    public List<AutoDto> getAllVinToServiceCompany(String nameServiceCompany) {
        List<AutoDto> result = new ArrayList<>();
        if (serviceCompanyRepository.findById(nameServiceCompany).isEmpty()) {
            List<AutoEntity> autoEntities = serviceCompanyRepository.findById(nameServiceCompany).get().getAutoEntities();
            for (int i = 0; i <= autoEntities.size() - 1; i++) {
                result.add(new AutoDto(autoEntities.get(i).getVinCode(), autoEntities.get(i).getServiceCompany().getNameServiceCompany()));
            }
        }
        return result;
    }
    @Override
    public Boolean addServiceCompany(String nameServiceCompany) {
        if (this.namesServiceCompany().add(nameServiceCompany)) {
            serviceCompanyRepository.save(new ServiceCompanyEntity());
            return true;
        }
        return false;
    }
    @Override
    public Boolean deleteServiceCompany(String nameServiceCompany) {
        if (!this.namesServiceCompany().add(nameServiceCompany)) {
            serviceCompanyRepository.deleteById(nameServiceCompany);
            return true;
        }
        return false;
    }
    @Override
    public Boolean deleteAllServiceCompany() {
        serviceCompanyRepository.deleteAll();
        return true;
    }
    @Override
    public Boolean updateServiceCompany(String oldSc, String newSC) {
        if(this.serviceCompanyRepository.findById(oldSc).isEmpty())
        {
            ServiceCompanyEntity serviceCompanyEntities = serviceCompanyRepository.findById(oldSc).get();
            serviceCompanyRepository.save(new ServiceCompanyEntity(newSC));
            List<AutoEntity> autoEntityList = autoRepository.findAll()
                    .stream()
                    .filter(autoEntity -> autoEntity.getServiceCompany().getNameServiceCompany().equals(oldSc))
                    .toList();
            serviceCompanyRepository.deleteById(oldSc);
            for(int i = 0; i<=autoEntityList.size()-1; i++)
            {
                autoRepository.deleteById(autoEntityList.get(i).getVinCode());
            }
            for(int i = 0; i<=autoEntityList.size()-1; i++)
            {
                autoEntityList.get(i).setServiceCompany(new ServiceCompanyEntity(newSC));
                autoRepository.save(autoEntityList.get(i));
            }
            return true;
        }
        return false;
    }

    private Set<String> namesServiceCompany() {
        List<ServiceCompanyDto> serviceCompanyDtoList = this.getAllServiceCompany();
        Set<String> nameServiceCompanySet = new HashSet<>();
        for (int i = 0; i <= serviceCompanyDtoList.size() - 1; i++) {
            nameServiceCompanySet.add(serviceCompanyDtoList.get(i).getNameServiceCompany());
        }
        return nameServiceCompanySet;
    }
}
