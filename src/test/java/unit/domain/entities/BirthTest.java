package unit.domain.entities;

import greetings.domain.DayOfYearDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BirthTest {
    private int birthMonth = 1;
    private int birthDay = 10;
    private int differentTodayMonth = 2;
    private int differentTodayDay = 11;
    private DayOfYearDate birthDate = new DayOfYearDate(birthMonth, birthDay);
    private DayOfYearDate differentDay = new DayOfYearDate(birthMonth, differentTodayDay);
    private DayOfYearDate differentMonth = new DayOfYearDate(differentTodayMonth, birthDay);
    private DayOfYearDate sameDate = new DayOfYearDate(birthMonth, birthDay);

    @Test
    void shouldBirthToday() {
        assertTrue(birthDate.equals(sameDate));
    }

    @Test
    void differentMonth() {
        assertFalse(birthDate.equals(differentMonth));
    }

    @Test
    void differentDay() {
        assertFalse(birthDate.equals(differentDay));
    }
}
