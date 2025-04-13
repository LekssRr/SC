package com.example.ServiceCompany.controller;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.dto.ServiceCompanyDto;
import com.example.ServiceCompany.service.ServiceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ServiceCompany")
public class ServiceCompanyController {

    private final ServiceCompanyService serviceCompanyService;

    public ServiceCompanyController(ServiceCompanyService serviceCompanyService) {
        this.serviceCompanyService = serviceCompanyService;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getAllServiceCompany() {
        List<String> result = new ArrayList<>();
        try {
            List<ServiceCompanyDto> serviceCompany = serviceCompanyService.getAllServiceCompany();
            result = serviceCompany.stream()
                    .map(ServiceCompanyDto::toString)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getServiceCompany(@PathVariable String id) {
        List<String> vinList = new ArrayList<>();
        try {
            vinList = serviceCompanyService.getAllVinToServiceCompany(id)
                    .stream()
                    .map(AutoDto::toString)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(vinList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(vinList);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> postServiceCompany(@PathVariable String id) {
        Boolean result = null;
        try {
            result = serviceCompanyService.addServiceCompany(id);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllServiceCompany() {
        Boolean result = false;
        try {
            result = serviceCompanyService.deleteAllServiceCompany();
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceCompany(@PathVariable String id) {
        Boolean result = false;
        try {
            result = serviceCompanyService.deleteServiceCompany(id);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping("/{oldSC}/{newSC}")
    public ResponseEntity putServiceCompany(@PathVariable String oldSC, String newSc) {
        Boolean result = false;
        try {
            result = serviceCompanyService.updateServiceCompany(oldSC, newSc);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
