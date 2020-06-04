package greetings.adapters;

import greetings.application.adapters.clock.TodayDateGenerator;
import greetings.domain.DayOfYearDate;

import java.time.LocalDate;

public class TodayLocalDateGenerator implements TodayDateGenerator {
    @Override
    public DayOfYearDate getTodayDate() {
        var localDate = LocalDate.now();
        return new DayOfYearDate(
                localDate.getMonthValue(),
                localDate.getDayOfMonth()
        );
    }
}
