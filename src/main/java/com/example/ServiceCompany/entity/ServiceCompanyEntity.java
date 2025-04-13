package com.example.ServiceCompany.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ServiceCompanyEntity {
    @Id
    private String nameServiceCompany;
    @OneToMany(mappedBy = "serviceCompany" , cascade = CascadeType.PERSIST)
    private List<AutoEntity> autoEntities;

    public ServiceCompanyEntity(String newNameServiceCompany)
    {
        this.nameServiceCompany = newNameServiceCompany;
        this.autoEntities = new ArrayList<>();
    }

    public ServiceCompanyEntity() {

    }

    public String getNameServiceCompany()
    {
        return nameServiceCompany;
    }
    public List<AutoEntity> getAutoEntities()
    {
        return new ArrayList<>(autoEntities);
    }
    public boolean addAutoToServiceCompany(AutoEntity addAuto)
    {
        Set<AutoEntity> currentAuto = new HashSet<>(autoEntities);
        if (currentAuto.add(addAuto))
        {
            this.autoEntities.add(addAuto);
            return true;
        }
        return false;
    }
    public boolean deleteAutoToServiceCompany(AutoEntity deleteAuto)
    {
        Set<AutoEntity> currentAuto = new HashSet<>(autoEntities);
        if (!currentAuto.add(deleteAuto))
        {
            currentAuto.remove(deleteAuto);
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        return nameServiceCompany;
    }
}
