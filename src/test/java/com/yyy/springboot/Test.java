package com.yyy.springboot;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

//        List<Integer> integers = Arrays.asList(1, 2);
//        List<Integer> integers1= Arrays.asList(1, 2);
//        System.out.println(integers1.containsAll(integers));


        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("test" + i);
            list1.add("test" + (i * 2));
        }
        System.out.println(checkDiffrent1(list, list1));

//        List<String> list = Arrays.asList(" 色", " 我色", " 蓝色", "ABCD");
//        List<String> list1 = Arrays.asList("ABCD", " 蓝色", " 色", " 我色");
//        System.out.println(" 蓝色".hashCode());
//        System.out.println("ABCD".hashCode());
//        System.out.println(list);


    }

    private static boolean checkDiffrent(List<String> list, List<String> list1) {
        long st = System.nanoTime();
        if (list.size() != list1.size()) {
            System.out.println("消耗时间：" + (System.nanoTime() - st));
            return false;
        }
        for (String str : list) {
            if (!list1.contains(str)) {
                System.out.println("消耗时间：" + (System.nanoTime() - st));
                return false;
            }
        }
        System.out.println("消耗时间：" + (System.nanoTime() - st));
        return true;
    }

    private static boolean checkDiffrent1(List<String> list, List<String> list1) {
        long st = System.nanoTime();
        list.sort(Comparator.comparing(String::hashCode));
        list1.sort(Comparator.comparing(String::hashCode));
        System.out.println("消耗时间为： " + (System.nanoTime() - st));
        return list.toString().equals(list1.toString());
    }


    @org.junit.Test
    public void test() {
        Map<Boolean, List<Integer>> collect = Stream.of(1, 2, 3).collect(Collectors.partitioningBy(x -> x > 1));

    }

    @org.junit.Test
    public void test2() {
        String str = "13000000000";
        boolean matches = str.matches("^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}");
        System.out.println(matches);

    }

    @org.junit.Test
    public void test3() {
        List<Object> list=new ArrayList<>();
        Collections.sort(list, (x,y)->{
            return x.toString().compareTo(y.toString());
        });

    }

    @org.junit.Test
    public void test4() throws FileNotFoundException {
        System.out.println(this.getClass().getResource("/a.jpg"));
    }

   @org.junit.Test
    public void test5(){
       File file = new File("D:\\webShopImage\\product\\100b456f-7a0e-4c49-a2b4-955da1a71cea.jpg");
       if(file.exists()){
           file.delete();
       }
   }

}
