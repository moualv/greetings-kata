package greetings.application.adapters.repository.dtos;

import java.time.LocalDate;

public class BirthDTO {
    private int day;
    private int year;
    private int month;

    public BirthDTO(
            int year,
            int month,
            int day
    ) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static BirthDTO fromLocalDate(LocalDate date) {
        return new BirthDTO(
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth()
        );
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BirthDTO) {
            var birth = (BirthDTO) o;
            return birth.getYear() == year &&
                    birth.getMonth() == month &&
                    birth.getDay() == day;
        } else {
            return false;
        }
    }
}
