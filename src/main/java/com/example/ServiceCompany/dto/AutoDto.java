package com.example.ServiceCompany.dto;

import com.example.ServiceCompany.entity.AutoEntity;

public class AutoDto {
    private String vinCode;
    private String serviceCompany;

    public AutoDto(String newVinCode, String newServiceCompany) {
        this.vinCode = newVinCode;
        this.serviceCompany = newServiceCompany;
    }
    public AutoDto(AutoEntity autoEntity)
    {
        this.vinCode = autoEntity.getVinCode();
        this.serviceCompany = autoEntity.getServiceCompany().getNameServiceCompany();
    }

    public String getVinCode() {
        return vinCode;
    }

    public String getNameServiceCompany() {
        return serviceCompany;
    }

    @Override
    public String toString() {
        return vinCode + " " + serviceCompany;
    }

    @Override
    public int hashCode() {
        return (this.vinCode.length() * 23) + (this.serviceCompany.length() * 4);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o == this) {
            return true;
        } else if (this.hashCode() == o.hashCode()) {
            if (this.getClass() == o.getClass()) {
                AutoDto a = (AutoDto) o;
                if (this.vinCode.equals(((AutoDto) o).getVinCode()) & this.serviceCompany.equals(((AutoDto) o).getNameServiceCompany())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}
