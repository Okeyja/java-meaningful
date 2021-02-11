package com.github.okeyja.java_meaningful.date_and_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * https://www.cnblogs.com/yw-ah/p/8512799.html
 */
public class ThreadNoneSafe {
    static SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    static String[] testdata = {"01-Jan-1999", "14-Feb-2001", "31-Dec-2007"};

    public static void main(String[] args) {
        Runnable[] r = new Runnable[testdata.length];
        for (int i = 0; i < r.length; i++) {
            final int i2 = i;
            r[i] = () -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        String str = testdata[i2];
                        String str2 = null;
                        /* synchronized(df) */
                        {
                            Date d = df.parse(str);
                            str2 = df.format(d);
                            System.out.println(
                                    "i: " + i2 + "\tj: " + j +
                                            "\tThreadID: " + Thread.currentThread().getId() +
                                            "\tThreadName: " + Thread.currentThread().getName() +
                                            "\t" + str + " " + str2
                            );
                        }
                        if (!str.equals(str2)) {
                            throw new RuntimeException("date conversion failed after " + j
                                    + " iterations. Expected [" + str + "] but got [" + str2 + "]");
                        }
                    }
                } catch (ParseException e) {
                    throw new RuntimeException("parse failed");
                }
            };
            new Thread(r[i]).start();
        }
    }
}
