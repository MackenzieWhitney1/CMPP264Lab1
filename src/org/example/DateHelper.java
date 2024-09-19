package org.example;
/*
this is a helper class that contains static methods for handling dates
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class DateHelper {

    // placeholder year value. not used when determining if date is a holiday
    private static final int phYear = 2000;

    private static final List<LocalDate> holidays = List.of(
            LocalDate.of(phYear, Month.JANUARY, 1),
            LocalDate.of(phYear, Month.FEBRUARY, 19),
            LocalDate.of(phYear, Month.MARCH, 29),
            LocalDate.of(phYear, Month.APRIL, 1),
            LocalDate.of(phYear, Month.MAY, 20),
            LocalDate.of(phYear, Month.AUGUST, 5),
            LocalDate.of(phYear, Month.SEPTEMBER, 2),
            LocalDate.of(phYear, Month.SEPTEMBER, 30),
            LocalDate.of(phYear, Month.OCTOBER, 14),
            LocalDate.of(phYear, Month.NOVEMBER, 11),
            LocalDate.of(phYear, Month.DECEMBER, 25)
    );

    // assert if given date is a holiday
    public static boolean assertIfDateIsHoliday(LocalDate date){
        return holidays.contains(date.withYear(phYear));
    }

    // assert if given date is a weekend
    public static boolean assertIfDateIsWeekend(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }
}
