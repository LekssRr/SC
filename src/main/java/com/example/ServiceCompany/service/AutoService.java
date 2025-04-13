package com.example.ServiceCompany.service;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.entity.AutoEntity;
import com.example.ServiceCompany.entity.ServiceCompanyEntity;
import com.example.ServiceCompany.repository.AutoRepository;
import com.example.ServiceCompany.repository.ServiceCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AutoService {
    private final ServiceCompanyRepository serviceCompanyRepository;
    private final AutoRepository autoRepository;

    public AutoService(ServiceCompanyRepository newServiceCompanyRepository, AutoRepository newAutoRepository) {
        this.serviceCompanyRepository = newServiceCompanyRepository;
        this.autoRepository = newAutoRepository;
    }
    private boolean isCurrentVin(String vinCode) {
        return vinCode.length() == 17;
    }

    public AutoDto getAuto(String vinCode) {
        if (isCurrentVin(vinCode)) {
            AutoDto result = null;
            if (!autoRepository.findById(vinCode).isEmpty()) {
                result = new AutoDto(autoRepository.findById(vinCode).get().getVinCode(), autoRepository.findById(vinCode).get().getServiceCompany().getNameServiceCompany());
            }
            return result;
        }
        return null;
    }

    public String getServiceCompany(AutoDto autoDto) {
        return autoDto.getNameServiceCompany();
    }

    public List<AutoDto> getAllAuto() {
        List<AutoEntity> autoEntities = autoRepository.findAll();
        return this.convertAutoEntityToAutoDto(autoEntities);
    }

    private List<AutoDto> convertAutoEntityToAutoDto(List<AutoEntity> autoEntities) {
        List<AutoDto> result = new ArrayList<>();
        for (int i = 0; i <= autoEntities.size() - 1; i++) {
            result.add(new AutoDto(autoEntities.get(i).getVinCode(), autoEntities.get(i).getServiceCompany().getNameServiceCompany()));
        }
        return result;
    }

    public List<AutoDto> getAllAutoToServiceCompany(String nameServiceCompany) {
        List<AutoEntity> listEntity = autoRepository.findAll()
                .stream()
                .filter(autoEntity -> {
                    return autoEntity.getServiceCompany().getNameServiceCompany().equals(nameServiceCompany);
                })
                .toList();
        return this.convertAutoEntityToAutoDto(listEntity);
    }

    public boolean addAuto(String vinCode, String nameServiceCompany) {
        if (isCurrentVin(vinCode)) {
            if (isServiceCompany(nameServiceCompany)) {
                if (this.isAuto(vinCode)) {
                    AutoEntity addAuto = new AutoEntity(vinCode, new ServiceCompanyEntity(nameServiceCompany));
                    autoRepository.save(addAuto);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteAuto(String vinCode) {
        if (isCurrentVin(vinCode)) {
            if (this.isAuto(vinCode)) {
                autoRepository.deleteById(vinCode);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAllAuto() {
        autoRepository.deleteAll();
        return true;
    }

    public boolean updateAuto(String vinCode, String newNameSC) {
        if (isCurrentVin(vinCode)) {
            if (isAuto(vinCode)) {
                if (this.isServiceCompany(newNameSC)) {
                    AutoDto cashAuto = this.getAuto(vinCode);
                    autoRepository.deleteById(vinCode);
                    this.addAuto(cashAuto.getVinCode(), newNameSC);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isServiceCompany(String nameServiceCompany) {
        List<ServiceCompanyEntity> serviceCompanyEntities = serviceCompanyRepository.findAll();
        Set<String> serviceCompanySet = new HashSet<>();
        serviceCompanySet = serviceCompanyEntities
                .stream()
                .map(ServiceCompanyEntity::getNameServiceCompany)
                .collect(Collectors.toSet());
        if (!serviceCompanySet.add(nameServiceCompany)) {
            return true;
        }
        return false;
    }

    public boolean isAuto(String vinCode) {
        List<AutoDto> autoDtos = this.getAllAuto();
        Set<String> vinSet = new HashSet<>();
        vinSet = autoDtos
                .stream()
                .map(AutoDto::getVinCode)
                .collect(Collectors.toSet());
        if (!vinSet.add(vinCode)) {
            return true;
        }
        return false;
    }
}
