package com.github.okeyja.java_meaningful.date_and_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalCalendar {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);
        System.out.println("Year: " + now.getYear());
        System.out.println("Month: " + now.getMonth());
        System.out.println("Month value: " + now.getMonthValue());
        System.out.println("Day of month: " + now.getDayOfMonth());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ms");
        System.out.println(now.format(fmt));
    }
}
