package com.example.eCard.util;

import com.example.eCard.domain.Patient;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

@UtilityClass
@Slf4j
public class CodeGenerator {

    public static String generate(@NonNull Patient patient) {
        long code = (long) (Math.PI * patient.getId()) * (patient.getName().length() + patient.getDisease().length());
        log.info("[TECH] [Info] Generated code: " + code);
        return String.valueOf(code);
    }

}
