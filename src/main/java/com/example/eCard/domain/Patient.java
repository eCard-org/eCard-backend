package com.example.eCard.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("patients")
public class Patient {
    @Id
    private Long id;
    private String code;
    private String name;
    private String disease;
}
