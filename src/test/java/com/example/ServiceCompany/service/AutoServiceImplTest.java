package com.example.ServiceCompany.service;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.entity.AutoEntity;
import com.example.ServiceCompany.entity.ServiceCompanyEntity;
import com.example.ServiceCompany.repository.AutoRepository;
import com.example.ServiceCompany.repository.ServiceCompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoServiceImplTest {
    @Mock
    private final AutoRepository autoRepositoryTest = Mockito.mock(AutoRepository.class);
    @Mock
    private final ServiceCompanyRepository serviceCompanyRepositoryTest = Mockito.mock(ServiceCompanyRepository.class);
    @InjectMocks
    private final AutoServiceImpl autoServiceImplTest = new AutoServiceImpl(serviceCompanyRepositoryTest, autoRepositoryTest);

    @Test
    public void getServiceCompanyTest() {
        String vinCodeTest = "11111111111111111";
        String nameServiceCompanyTest = "SC-Test";
        AutoDto testAutoDto = new AutoDto(vinCodeTest, nameServiceCompanyTest);
        Assertions.assertEquals(autoServiceImplTest.getServiceCompany(testAutoDto), nameServiceCompanyTest);
    }

    @Test
    public void getAutoTest() {
        String vin = "00000000000000000";
        ServiceCompanyEntity serviceCompanyEntity = new ServiceCompanyEntity("SC-Test");
        AutoEntity testAutoEntity = new AutoEntity(vin, serviceCompanyEntity);
        AutoDto autoDto = new AutoDto(vin, serviceCompanyEntity.getNameServiceCompany());
        Optional<AutoEntity> optionalAutoEntity = Optional.ofNullable(testAutoEntity);
        Mockito.when(autoRepositoryTest.findById(vin)).thenReturn(optionalAutoEntity);
        Assertions.assertEquals(autoServiceImplTest.getAuto(vin), autoDto);
    }

    @Test
    public void getAllAutoTest() {
        List<AutoEntity> autoEntities = new ArrayList<>();
        List<AutoDto> autoDto = new ArrayList<>();
        String vin = "00000000000000000";
        String vin1 = "00000000000000001";
        String vin2 = "00000000000000002";
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-1");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-2");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-3");
        AutoEntity test = new AutoEntity(vin, sc);
        AutoEntity test1 = new AutoEntity(vin1, sc1);
        AutoEntity test2 = new AutoEntity(vin2, sc2);
        autoEntities.add(test);
        autoEntities.add(test1);
        autoEntities.add(test2);
        for (int i = 0; i <= autoEntities.size() - 1; i++) {
            autoDto.add(new AutoDto(autoEntities.get(i).getVinCode(), autoEntities.get(i).getServiceCompany().getNameServiceCompany()));
        }
        Mockito.when(autoRepositoryTest.findAll()).thenReturn(autoEntities);
        Assertions.assertEquals(autoServiceImplTest.getAllAuto(), autoDto);
    }

    @Test
    public void getAllAutoToServiceCompanyTest() {
        List<AutoEntity> autoEntities = new ArrayList<>();
        List<AutoDto> autoDto = new ArrayList<>();
        String vin = "00000000000000000";
        String vin1 = "00000000000000001";
        String vin2 = "00000000000000002";
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-1");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-2");
        AutoEntity test = new AutoEntity(vin, sc);
        AutoEntity test1 = new AutoEntity(vin1, sc1);
        AutoEntity test2 = new AutoEntity(vin2, sc);
        autoEntities.add(test);
        autoEntities.add(test1);
        autoEntities.add(test2);
        autoDto.add(new AutoDto(test.getVinCode(), test.getServiceCompany().getNameServiceCompany()));
        autoDto.add(new AutoDto(test2.getVinCode(), test2.getServiceCompany().getNameServiceCompany()));
        Mockito.when(autoRepositoryTest.findAll()).thenReturn(autoEntities);
        Assertions.assertEquals(autoServiceImplTest.getAllAutoToServiceCompany(sc.getNameServiceCompany()), autoDto);
    }

    @Test
    public void addAutoTest() {
        List<AutoEntity> autoEntities = new ArrayList<>();
        List<AutoDto> autoDto = new ArrayList<>();
        List<ServiceCompanyEntity> serviceCompanyEntities = new ArrayList<>();
        String vin = "00000000000000000";
        String vin1 = "00000000000000001";
        String vin2 = "00000000000000002";
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-1");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-2");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-3");
        AutoEntity test = new AutoEntity(vin, sc);
        AutoEntity test1 = new AutoEntity(vin1, sc1);
        AutoEntity test2 = new AutoEntity(vin2, sc2);
        AutoEntity test3 = new AutoEntity("12121", new ServiceCompanyEntity("SC-4"));
        autoEntities.add(test);
        autoEntities.add(test1);
        autoEntities.add(test2);
        serviceCompanyEntities.add(sc);
        serviceCompanyEntities.add(sc1);
        Mockito.when(serviceCompanyRepositoryTest.findAll()).thenReturn(serviceCompanyEntities);
        for (int i = 0; i <= autoEntities.size() - 1; i++) {
            autoDto.add(new AutoDto(autoEntities.get(i).getVinCode(), autoEntities.get(i).getServiceCompany().getNameServiceCompany()));
        }
        Mockito.when(autoRepositoryTest.findAll()).thenReturn(autoEntities);
        Assertions.assertEquals(autoServiceImplTest.addAuto(test.getVinCode(), test.getServiceCompany().getNameServiceCompany()), true);
        Assertions.assertEquals(autoServiceImplTest.addAuto(test3.getVinCode(), test3.getServiceCompany().getNameServiceCompany()), false);

    }

    @Test
    public void deleteAutoTest() {
        List<AutoEntity> autoEntities = new ArrayList<>();
        List<AutoDto> autoDto = new ArrayList<>();
        String vin = "00000000000000000";
        String vin1 = "00000000000000001";
        String vin2 = "00000000000000002";
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-1");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-2");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-3");
        AutoEntity test = new AutoEntity(vin, sc);
        AutoEntity test1 = new AutoEntity(vin1, sc1);
        AutoEntity test2 = new AutoEntity(vin2, sc2);
        autoEntities.add(test);
        autoEntities.add(test1);
        autoEntities.add(test2);
        for (int i = 0; i <= autoEntities.size() - 1; i++) {
            autoDto.add(new AutoDto(autoEntities.get(i).getVinCode(), autoEntities.get(i).getServiceCompany().getNameServiceCompany()));
        }
        Mockito.when(autoRepositoryTest.findAll()).thenReturn(autoEntities);
        Assertions.assertEquals(autoServiceImplTest.deleteAuto(vin), true);
        Assertions.assertEquals(autoServiceImplTest.deleteAuto("2222"), false);
    }

    @Test
    public void updateAutoTest() {

    }

    @Test
    public void isServiceCompanyTest() {
        List<ServiceCompanyEntity> testList = new ArrayList<>();
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-1");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-2");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-3");
        testList.add(sc);
        testList.add(sc1);
        testList.add(sc2);
        Mockito.when(serviceCompanyRepositoryTest.findAll()).thenReturn(testList);
        Assertions.assertEquals(autoServiceImplTest.isServiceCompany(sc.getNameServiceCompany()), true);
        Assertions.assertEquals(autoServiceImplTest.isServiceCompany("SC-222"), false);
    }

    @Test
    public void isAutoTest() {
        List<AutoEntity> autoEntities = new ArrayList<>();
        List<AutoDto> autoDto = new ArrayList<>();
        String vin = "00000000000000000";
        String vin1 = "00000000000000001";
        String vin2 = "00000000000000002";
        ServiceCompanyEntity sc = new ServiceCompanyEntity("SC-1");
        ServiceCompanyEntity sc1 = new ServiceCompanyEntity("SC-2");
        ServiceCompanyEntity sc2 = new ServiceCompanyEntity("SC-3");
        AutoEntity test = new AutoEntity(vin, sc);
        AutoEntity test1 = new AutoEntity(vin1, sc1);
        AutoEntity test2 = new AutoEntity(vin2, sc2);
        autoEntities.add(test);
        autoEntities.add(test1);
        autoEntities.add(test2);
        for (int i = 0; i <= autoEntities.size() - 1; i++) {
            autoDto.add(new AutoDto(autoEntities.get(i).getVinCode(), autoEntities.get(i).getServiceCompany().getNameServiceCompany()));
        }
        Mockito.when(autoRepositoryTest.findAll()).thenReturn(autoEntities);
        Assertions.assertEquals(autoServiceImplTest.isAuto(vin), true);
        Assertions.assertEquals(autoServiceImplTest.isAuto(vin1), true);
        Assertions.assertEquals(autoServiceImplTest.isAuto(vin2), true);
        Assertions.assertEquals(autoServiceImplTest.isAuto("31231"), false);
    }

}

