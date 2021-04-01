package com.yyy.springboot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test1() {
       /* LocalDate now = LocalDate.now();
        System.out.println(now);*/
//        int []i={1,24,4,76,9};
//
//        for (int a:i
//             ) {
//            if(a==4)
//                continue;
//            System.out.println(a);
//        }

        List<Integer> integers = Arrays.asList(1, 2);
        List<Integer> integers1= Arrays.asList(1, 2);
        System.out.println(integers1.containsAll(integers));
    }
}
