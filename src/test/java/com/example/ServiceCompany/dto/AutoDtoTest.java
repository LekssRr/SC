package com.example.ServiceCompany.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoDtoTest {
    private String testVin = "11111111111111111";
    private String testNameServiceCompany = "SC_Test";
    private AutoDto testAutoDto = new AutoDto(testVin, testNameServiceCompany);

    @Test
    void getVinCodeTest() {
        Assertions.assertEquals(testAutoDto.getVinCode(), testVin);
    }

    @Test
    void getServiceCompanyName() {
        Assertions.assertEquals(testAutoDto.getNameServiceCompany(), testNameServiceCompany);
    }

    @Test
    void toStringTest() {
        String res = "11111111111111111 SC_Test";
        Assertions.assertEquals(testAutoDto.toString(), res);
    }
}
