package com.example.ServiceCompany.controller;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.service.AutoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;


public class AutoControllerTest {
    @Mock
    private AutoServiceImpl autoServiceImpl = Mockito.mock(AutoServiceImpl.class);
    @InjectMocks
    private AutoController autoController = new AutoController(autoServiceImpl);

    @Test
    public void getAllAutoTest() {
        List<AutoDto> testDto = new ArrayList<>();
        AutoDto autoDto1 = new AutoDto("1111", "12311");
        AutoDto autoDto2 = new AutoDto("1112", "12312");
        AutoDto autoDto3 = new AutoDto("1113", "12313");
        testDto.add(autoDto1);
        testDto.add(autoDto2);
        testDto.add(autoDto3);
        Mockito.when(autoServiceImpl.getAllAuto()).thenReturn(testDto);
        List<String> stringList = new ArrayList<>();
        stringList.add(autoDto1.toString());
        stringList.add(autoDto2.toString());
        stringList.add(autoDto3.toString());
        ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(stringList, HttpStatus.OK);
        Assertions.assertEquals(autoController.getAllAuto(), responseEntity);
    }

    @Test
    public void getAutoTest() {
        AutoDto autoDto1 = new AutoDto("11111111111111111", "SC-Test");
        Mockito.when(autoServiceImpl.getAuto("11111111111111111")).thenReturn(autoDto1);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(autoDto1.toString(), HttpStatus.OK);
        Assertions.assertEquals(autoController.getAuto("11111111111111111"), responseEntity);
    }

    @Test
    public void postAutoTest() {
        AutoDto autoDto1 = new AutoDto("11111111111111111", "SC-Test");
        Mockito.when(autoServiceImpl.addAuto(autoDto1.getVinCode(), autoDto1.getNameServiceCompany())).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity("true", HttpStatus.OK);
        Assertions.assertEquals(autoController.postAuto(autoDto1.getVinCode(), autoDto1.getNameServiceCompany()), responseEntity);
    }

    @Test
    public void deleteAllAutoTest() {
        Mockito.when(autoServiceImpl.deleteAllAuto()).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity("true", HttpStatus.OK);
        Assertions.assertEquals(autoController.deleteAllAuto(), responseEntity);
    }

    @Test
    public void deleteAutoTest() {
        AutoDto autoDto1 = new AutoDto("11111111111111111", "SC-Test");
        Mockito.when(autoServiceImpl.deleteAuto(autoDto1.getVinCode())).thenReturn(true);
        ResponseEntity<String> responseEntity = new ResponseEntity("true", HttpStatus.OK);
        Assertions.assertEquals(autoController.deleteAuto(autoDto1.getVinCode()), responseEntity);
    }
}
