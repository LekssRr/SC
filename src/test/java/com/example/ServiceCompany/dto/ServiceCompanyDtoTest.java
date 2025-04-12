package com.example.ServiceCompany.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ServiceCompanyDtoTest {
    String testNameServiceCompany = "TestServiceCompany";
    ServiceCompanyDto serviceCompanyDtoTest = new ServiceCompanyDto(testNameServiceCompany);
    List<String> allAutoToDtoTest = new ArrayList<>();

    @Test
    void getNameServiceCompanyTest() {
        Assertions.assertEquals(serviceCompanyDtoTest.getNameServiceCompany(), testNameServiceCompany);
    }

    @Test
    void getAutoEntitiesTest() {
        allAutoToDtoTest.add("11111");
        allAutoToDtoTest.add("222222");
        serviceCompanyDtoTest.addAutoToServiceCompany("11111");
        serviceCompanyDtoTest.addAutoToServiceCompany("222222");
        Assertions.assertEquals(serviceCompanyDtoTest.getAutoEntities(), allAutoToDtoTest);
    }

    @Test
    void addAutoToServiceCompany() {
        serviceCompanyDtoTest.addAutoToServiceCompany("22222");
        Assertions.assertEquals(serviceCompanyDtoTest.getAutoEntities().get(0), "22222");
    }
}
