package builders.repository.dtos;

import greetings.application.adapters.repository.dtos.BirthDTO;

import java.time.LocalDate;

public class BirthDTOBuilder {

    private int year;
    private int month;
    private int day;

    private BirthDTOBuilder() {
        var now = LocalDate.now();
        initFromDate(now);
    }

    private void initFromDate(LocalDate now) {
        this.year = now.getYear();
        this.month = now.getMonth().getValue();
        this.day = now.getDayOfMonth();
    }

    public static BirthDTOBuilder create() {
        return new BirthDTOBuilder();
    }

    public BirthDTOBuilder birthdayToday() {
        var date = LocalDate.now().minusYears(20);
        initFromDate(date);
        return this;
    }

    public BirthDTO build() {
        return new BirthDTO(
                year,
                month,
                day
        );
    }
}
