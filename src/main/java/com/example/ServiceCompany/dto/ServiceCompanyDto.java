package com.example.ServiceCompany.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ServiceCompanyDto implements Function<ServiceCompanyDto, String> {
    private String nameServiceCompany;
    private List<String> autoEntities;

    public ServiceCompanyDto(String newNameServiceCompany) {
        this.nameServiceCompany = newNameServiceCompany;
        this.autoEntities = new ArrayList<>();
    }

    public String getNameServiceCompany() {
        return nameServiceCompany;
    }

    public List<String> getAutoEntities() {
        return new ArrayList<>(autoEntities);
    }

    public void addAutoToServiceCompany(String addAuto) {
        this.autoEntities.add(addAuto);
    }

    @Override
    public String toString() {
        return nameServiceCompany;
    }

    @Override
    public int hashCode() {
        return (this.nameServiceCompany.length() * 45) + (this.autoEntities.size() * 32);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        }
        if (this.hashCode() == o.hashCode()) {
            if (this.getClass() == o.getClass()) {
                ServiceCompanyDto serviceCompanyDto = (ServiceCompanyDto) o;
                if (this.nameServiceCompany.equals(serviceCompanyDto.nameServiceCompany) & this.autoEntities.equals(serviceCompanyDto.autoEntities)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public String apply(ServiceCompanyDto serviceCompanyDto) {
        return serviceCompanyDto.nameServiceCompany;
    }
}
