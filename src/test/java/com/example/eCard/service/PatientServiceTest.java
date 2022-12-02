package com.example.eCard.service;

import com.example.eCard.exception.BusinessException;
import com.example.eCard.model.Patient;
import com.example.eCard.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PatientService.class)
class PatientServiceTest {

    @Autowired
    private PatientService service;

    @MockBean
    private PatientRepository repository;

    private List<Patient> data;

    @BeforeEach
    void initData() {
        data = List.of(
                new Patient(1L, 101L, "John", "Cancer"),
                new Patient(2L, 102L, "Andrew", "Other")
        );
    }

    @Test
    void getAll() {
        // when
        when(repository.findAll()).thenReturn(data);

        // then
        assertEquals(data, service.getAll());
    }

    @Test
    void getByCodeShouldReturnData() {
        // when
        when(repository.findByCode(101L)).thenReturn(Optional.of(data.get(0)));

        // then
        assertEquals(data.get(0), service.getByCode(101L));
    }

    @Test
    void getByCodeShouldThrowException() {
        // when
        when(repository.findByCode(103L)).thenReturn(Optional.empty());

        // then
        assertThrows(BusinessException.class, () -> service.getByCode(103L), "Пациент с таким кодом не найден. Код: 103");
    }

    @Test
    void saveShouldReturnObject() {
        // given
        Patient patient = new Patient(3L, 103L, "Ivan", "Other");

        // when
        when(repository.save(patient)).thenReturn(patient);

        // then
        assertEquals(patient.getCode(), repository.save(patient).getCode());
    }

    @Test
    void updateShouldReturnObject() {
        // given
        Patient patient = new Patient(3L, 103L, "Ivan", "Other");

        // when
        when(repository.findById(3L)).thenReturn(Optional.of(patient));
        when(repository.save(patient)).thenReturn(patient);

        // then
        assertEquals(patient, service.update(3L, patient));
    }

    @Test
    void updateShouldThrowException() {
        // when
        when(repository.findById(100L)).thenReturn(Optional.empty());

        // then
        assertThrows(BusinessException.class, () -> service.update(100L, null), "Пациент с кодом 100 не найден");
    }

}