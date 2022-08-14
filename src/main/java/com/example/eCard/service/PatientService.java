package com.example.eCard.service;

import com.example.eCard.domain.Patient;
import com.example.eCard.exception.BusinessException;
import com.example.eCard.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public List<Patient> getAll() {
        log.info("[TECH] [Info] Getting all patients");
        return repository.findAll();
    }

    public Patient getByCode(String code) {
        Optional<Patient> optionalPatient = repository.findByCode(code);

        if (optionalPatient.isPresent()) {
            log.info("[TECH] [Info] Getting one patient by code: " + code);
            return optionalPatient.get();
        }

        throw new BusinessException("Пациент с таким кодом не найден. Код: " + code);
    }

    @Transactional
    public Patient save(Patient patient) {
        if (repository.findById(patient.getId()).isPresent()) {
            return update(patient);
        }

        log.info("[TECH] [Info] Saving patient");
        return repository.save(patient);
    }

    @Transactional
    public Patient update(Patient patient) {
        Optional<Patient> optionalPatient = repository.findById(patient.getId());

        if (optionalPatient.isPresent()) {
            optionalPatient.get().setCode(patient.getCode());
            optionalPatient.get().setName(patient.getName());
            optionalPatient.get().setDisease(patient.getDisease());

            log.info("[TECH] [Info] Updating patient");
            return repository.save(optionalPatient.get());
        }

        throw new BusinessException("Пациент с id: " + patient.getId() + " не найден");
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
