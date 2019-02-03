package org.fabriki.utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DataHoraUtils {
    public static LocalDateTime dataHoraAtualEmUTC() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    public static LocalDate dataAtualEmUTC() {
        return LocalDate.now(Clock.systemUTC());
    }

}
