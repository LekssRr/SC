package com.example.ServiceCompany.controller;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.dto.ServiceCompanyDto;
import com.example.ServiceCompany.service.ServiceCompanyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

public class ServiceCompanyControllerTest {
    @Mock
    private ServiceCompanyServiceImpl serviceCompanyServiceImpl = Mockito.mock(ServiceCompanyServiceImpl.class);
    @InjectMocks
    private ServiceCompanyController serviceCompanyController = new ServiceCompanyController(serviceCompanyServiceImpl);


    @Test
    public void getAllServiceCompanyTest() {
        List<String> result = new ArrayList<>();
        List<ServiceCompanyDto> serviceCompanyDtoList = new ArrayList<>();
        ServiceCompanyDto serviceCompanyDto1 = new ServiceCompanyDto("SC-1");
        ServiceCompanyDto serviceCompanyDto2 = new ServiceCompanyDto("SC-2");
        ServiceCompanyDto serviceCompanyDto3 = new ServiceCompanyDto("SC-3");
        result.add(serviceCompanyDto1.toString());
        result.add(serviceCompanyDto2.toString());
        result.add(serviceCompanyDto3.toString());
        serviceCompanyDtoList.add(serviceCompanyDto1);
        serviceCompanyDtoList.add(serviceCompanyDto2);
        serviceCompanyDtoList.add(serviceCompanyDto3);
        Mockito.when(serviceCompanyServiceImpl.getAllServiceCompany()).thenReturn(serviceCompanyDtoList);
        ResponseEntity<List<String>> responseEntity = new ResponseEntity(result, HttpStatus.OK);
        Assertions.assertEquals(serviceCompanyController.getAllServiceCompany(), responseEntity);
    }

    @Test
    public void getServiceCompanyTest() {
        AutoDto autoDto = new AutoDto("22222222222222222", "SC-5");
        AutoDto autoDto1 = new AutoDto("22222222222222221", "SC-5");
        AutoDto autoDto2 = new AutoDto("22222222222222223", "SC-5");
        List<AutoDto> autoDtoList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        autoDtoList.add(autoDto1);
        autoDtoList.add(autoDto2);
        autoDtoList.add(autoDto);
        stringList.add(autoDto1.toString());
        stringList.add(autoDto2.toString());
        stringList.add(autoDto.toString());
        Mockito.when(serviceCompanyServiceImpl.getAllVinToServiceCompany(autoDto.getNameServiceCompany())).thenReturn(autoDtoList);
        ResponseEntity<List<String>> responseEntity = new ResponseEntity(stringList, HttpStatus.OK);
        Assertions.assertEquals(serviceCompanyController.getServiceCompany("SC-5"), responseEntity);
    }

    @Test
    public void postServiceCompanyTest() {
        String sc = "SC-1";
        Mockito.when(serviceCompanyServiceImpl.addServiceCompany(sc)).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("true", HttpStatus.OK);
        Assertions.assertEquals(serviceCompanyController.postServiceCompany(sc), responseEntity);
    }

    @Test
    public void deleteAllServiceCompanyTest() {
        Mockito.when(serviceCompanyServiceImpl.deleteAllServiceCompany()).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("true", HttpStatus.OK);
        Assertions.assertEquals(serviceCompanyController.deleteAllServiceCompany(), responseEntity);
    }

    @Test
    public void deleteServiceCompanyTest() {
        String sc = "SC-1";
        Mockito.when(serviceCompanyServiceImpl.deleteServiceCompany(sc)).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("true", HttpStatus.OK);
        Assertions.assertEquals(serviceCompanyController.deleteServiceCompany(sc), responseEntity);
    }

    @Test
    public void putServiceCompanyTest() {
        String sc = "SC-1";
        String sc2 = "SC-2";
        Mockito.when(serviceCompanyServiceImpl.updateServiceCompany(sc, sc2)).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("true", HttpStatus.OK);
        Assertions.assertEquals(serviceCompanyController.putServiceCompany(sc, sc2), responseEntity);
    }
}
