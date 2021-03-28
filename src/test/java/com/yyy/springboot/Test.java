package com.yyy.springboot;

import java.time.LocalDate;
import java.time.LocalTime;

public class Test {

    @org.junit.Test
    public void test1() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
    }
}
