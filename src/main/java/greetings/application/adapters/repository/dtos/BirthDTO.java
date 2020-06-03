package greetings.application.adapters.repository.dtos;

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

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }
}
