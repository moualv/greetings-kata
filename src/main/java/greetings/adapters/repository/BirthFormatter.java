package greetings.adapters.repository;

import greetings.application.adapters.repository.dtos.BirthDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthFormatter {
    public static String format(BirthDTO birth) {
        return LocalDate.of(birth.getYear(), birth.getMonth(), birth.getDay()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
