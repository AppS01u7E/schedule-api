package com.appsolute.soom.scheduleapi.util;

import java.time.LocalDate;
import java.time.YearMonth;


public class DateUtil {
    public static LocalDate getLastDayOfWeek(Integer year, Integer month, Integer week) {
        return LocalDate.of(year, month, 1)
                .plusDays(7 - LocalDate.of(year, month, 1).getDayOfWeek().getValue())
                .plusDays((week-1) * 7L);
    }

    public static LocalDate getFirstDayOfWeek(Integer year, Integer month, Integer week) {
        if(week == 1) return LocalDate.of(year, month, 1);
        else return LocalDate.of(year, month, 1)
                .minusDays(7 - LocalDate.of(year, month, 1).getDayOfWeek().getValue())
                .plusDays((week-1) * 7L);
    }
    public static LocalDate getFirstDayOfMonth(Integer year, Integer month) {
        return LocalDate.of(year, month, 1);
    }
    public static LocalDate getLastDayOfMonth(Integer year, Integer month) {
        return YearMonth.of(year, month).atEndOfMonth();
    }
}
