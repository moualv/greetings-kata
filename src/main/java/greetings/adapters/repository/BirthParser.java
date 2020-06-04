package greetings.adapters.repository;

import greetings.application.adapters.repository.dtos.BirthDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthParser {
    public static BirthDTO parse(String birthStr) {
        var date = LocalDate.parse(birthStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return BirthDTO.fromLocalDate(date);
    }
}
