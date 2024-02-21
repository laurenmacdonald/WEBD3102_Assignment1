package com.example.webd3102_assignment1.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DueDateDefiner {
    public static String defineRelativeDueDate(LocalDate dueDate, boolean completeStatus) {
        LocalDate currentDate = LocalDate.now();
        if (dueDate.isBefore(currentDate) && !completeStatus) {
            return "Overdue";
        } else if (dueDate.isBefore(currentDate) && completeStatus) {
            return "Complete";
        } else if (dueDate.isEqual(currentDate)) {
            return "Today";
        } else if (dueDate.isEqual(currentDate.plusDays(1))) {
            return "Tomorrow";
        } else if (dueDate.isAfter(currentDate) && dueDate.isBefore(currentDate.plusDays(8))) {
            long daysDifference = ChronoUnit.DAYS.between(currentDate, dueDate);
            return daysDifference + " Days";
        } else {
            long daysDifference = ChronoUnit.DAYS.between(currentDate, dueDate);
            return daysDifference + " days";
        }
    }

    public static String defineDayOfWeek(LocalDate dueDate, Locale locale){
        DayOfWeek day = dueDate.getDayOfWeek();
        return day.getDisplayName(TextStyle.FULL, locale);
    }
}
