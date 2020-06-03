package unit.builders.repository.dtos;

import com.github.javafaker.Faker;
import greetings.application.adapters.repository.dtos.BirthDTO;

import java.time.LocalDate;
import java.time.Month;

public class BirthDTOBuilder {

    private final int year;
    private final int month;
    private final int day;

    private BirthDTOBuilder() {
        var now = LocalDate.now();
        this.year = now.getYear();
        this.month = now.getMonth().getValue();
        this.day = now.getDayOfMonth();
    }

    public static BirthDTOBuilder create() {
        return new BirthDTOBuilder();
    }

    public BirthDTO build() {
        return new BirthDTO(
                year,
                month,
                day
        );
    }
}
