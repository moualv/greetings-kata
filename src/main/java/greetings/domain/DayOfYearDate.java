package greetings.domain;

public class DayOfYearDate {
    private int month;
    private int day;

    public DayOfYearDate(int month, int day) {
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DayOfYearDate) {
            var date = (DayOfYearDate) o;
            return month == date.getMonth() && day == date.getDay();
        } else {
            return false;
        }
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
