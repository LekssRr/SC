package com.example.ServiceCompany.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Auto")
public class AutoEntity {
    @Id
    @Column(name = "vin")
    private String vinCode;
    @ManyToOne
    @Column(name = "service_company")
    private ServiceCompanyEntity serviceCompany;

    public AutoEntity(String newVinCode, ServiceCompanyEntity newServiceCompany) {
        this.vinCode = newVinCode;
        this.serviceCompany = newServiceCompany;
    }

    public AutoEntity() {

    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String newVin) {
        this.vinCode = newVin;
    }

    public ServiceCompanyEntity getServiceCompany() {
        return serviceCompany;
    }

    public void setServiceCompany(ServiceCompanyEntity serviceCompany) {
        this.serviceCompany = serviceCompany;
    }

    @Override
    public String toString() {
        return vinCode;
    }
}
