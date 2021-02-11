package com.github.okeyja.java_meaningful.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "123";
        //构建一个多行的字符串
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            int k = new Random().nextInt() % 2;
            if (k == 0) {
                stb.append(s1).append("\n");
            } else {
                stb.append(s2).append("\n");
            }
        }

        Pattern p2 = Pattern.compile("\\d+", Pattern.DOTALL);
        Pattern p1 = Pattern.compile("\\d+");
        String ts = stb.toString();
        String[] sar = ts.split("\n");
        test1(sar);
        test2(ts, p2);
        test3(sar, p1);


    }

    public static void test1(String[] sar) {
        long st = System.nanoTime();
        List<String> l = new ArrayList<String>();
        for (String s : sar) {
            if (s.matches("\\d+")) {
                l.add(s);
            }
        }
        System.out.println("1Size" + l.size());
        long et = System.nanoTime();
        System.out.println("test1:" + (et - st) + "纳秒");
    }


    public static void test2(String s, Pattern p) {
        long st = System.nanoTime();
        List<String> l = new ArrayList<String>();
        Matcher m = p.matcher(s);
        while (m.find()) {
            l.add(m.group());
        }
        System.out.println("2Size" + l.size());
        long et = System.nanoTime();
        System.out.println("test2:" + (et - st) + "纳秒");
    }

    public static void test3(String[] sar, Pattern p1) {
        long st = System.nanoTime();
        List<String> l = new ArrayList<String>();
        for (String s : sar) {
            Matcher m = p1.matcher(s);
            if (m.matches()) {
                l.add(s);
            }
        }
        System.out.println("3Size" + l.size());
        long et = System.nanoTime();
        System.out.println("test3:" + (et - st) + "纳秒");
    }

}