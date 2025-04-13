package com.example.ServiceCompany.controller;

import com.example.ServiceCompany.dto.AutoDto;
import com.example.ServiceCompany.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auto")
public class AutoController {

    private final AutoService autoService;

    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getAllAuto() {
        List<String> result = new ArrayList<>();
        try {
            List<AutoDto> autoDtos = autoService.getAllAuto();
            result = autoDtos.stream()
                    .map(AutoDto::toString)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAuto(@PathVariable String id) {
        try {
            return ResponseEntity.ok(autoService.getAuto(id).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping("/{id}/{sc}")
    public ResponseEntity<String> postAuto(@PathVariable String id, @PathVariable String sc) {
        try {
            Boolean res = autoService.addAuto(id, sc);
            return ResponseEntity.ok(res.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllAuto() {
        String result = "Ошибка";
        try {
            Boolean res = autoService.deleteAllAuto();
            return ResponseEntity.ok(res.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuto(@PathVariable String id) {
        try {
            Boolean result = autoService.deleteAuto(id);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping("/put/")
    public ResponseEntity putAuto() {
        try {
            return ResponseEntity.ok("PUT");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
