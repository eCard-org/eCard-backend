package com.example.eCard.controller;

import com.example.eCard.model.Patient;
import com.example.eCard.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @GetMapping
    public List<Patient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{code}")
    public Patient getByCode(@PathVariable Long code) {
        return service.getByCode(code);
    }

    @PutMapping
    public Patient save(@RequestBody Patient patient) {
        return service.save(patient);
    }

    @PatchMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient patient) {
        return service.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}


