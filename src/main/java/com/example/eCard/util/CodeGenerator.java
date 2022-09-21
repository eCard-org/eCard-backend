package com.example.eCard.util;

import com.example.eCard.model.Patient;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

@Slf4j
@UtilityClass
public class CodeGenerator {

    // TODO: complete this method
    public String generate(@NonNull Patient patient) {
        long code = (long) (Math.PI * patient.getId()) * (patient.getName().length() + patient.getDisease().length());
        log.info("[TECH] [Info] Generated code: " + code);
        return String.valueOf(code);
    }

}
