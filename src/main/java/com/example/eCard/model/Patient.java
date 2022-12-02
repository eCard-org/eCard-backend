package com.example.eCard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("patients")
public class Patient {
    @Id
    private Long id;
    private Long code;
    private String name;
    private String disease;
}
