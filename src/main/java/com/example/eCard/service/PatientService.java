package com.example.eCard.service;

import com.example.eCard.model.Patient;
import com.example.eCard.exception.BusinessException;
import com.example.eCard.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private static final String PATIENT_NOT_FOUND_MESSAGE = "Пациент с кодом %d не найден";

    private final PatientRepository repository;

    public List<Patient> getAll() {
        log.info("[Tech] [INFO] Getting all patients");
        return repository.findAll();
    }

    public Patient getByCode(Long code) {
        Optional<Patient> optionalPatient = repository.findByCode(code);

        if (optionalPatient.isPresent()) {
            log.info("[Tech] [INFO] Getting one patient by code: " + code);
            return optionalPatient.get();
        }

        throw new BusinessException("Пациент с таким кодом не найден. Код: " + code);
    }

    public Patient save(Patient patient) {
        if (repository.findById(patient.getId()).isPresent()) return update(patient.getId(), patient);

        log.info("[Tech] [INFO] Saving patient");
        return repository.save(patient);
    }

    public Patient update(Long id, Patient patient) {
        Optional<Patient> optionalPatient = repository.findById(id);

        if (optionalPatient.isEmpty()) throw new BusinessException(String.format(PATIENT_NOT_FOUND_MESSAGE, id));

        optionalPatient.get().setCode(patient.getCode());
        optionalPatient.get().setName(patient.getName());
        optionalPatient.get().setDisease(patient.getDisease());

        log.info("[Tech] [INFO] Updating patient");
        return repository.save(optionalPatient.get());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
