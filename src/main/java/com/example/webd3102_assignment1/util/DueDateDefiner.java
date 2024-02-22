package com.example.webd3102_assignment1.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Class to get the relative due date (today, tomorrow, complete, overdue, 2 days, 3 days, etc.) and the day of the week
 */
public class DueDateDefiner {
    /**
     *
     * @param dueDate
     * @param completeStatus
     * @return relative due date
     * <br>
     * Static method. Uses LocalDate as reference for relative date. Checks to see if task is complete to define whether Overdue.
     */
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

    /**
     *
     * @param dueDate
     * @param locale
     * @return day of the week
     * <br>
     * Static method. Uses DateOfWeek class to determine the day of the week based on due date provided. Returns Monday, Tuesday, etc.
     */
    public static String defineDayOfWeek(LocalDate dueDate, Locale locale){
        DayOfWeek day = dueDate.getDayOfWeek();
        return day.getDisplayName(TextStyle.FULL, locale);
    }
}
