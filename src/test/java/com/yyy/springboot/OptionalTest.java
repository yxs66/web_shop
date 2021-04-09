package com.yyy.springboot;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.exception.TypeConverterException;
import com.yyy.springboot.util.ResultEnum;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class OptionalTest {

//    @Test
//    public void test1(Product p) {
////        Optional<Object> empty = Optional.empty();
//
//        Optional<Product> empty = Optional.ofNullable(p);
//
////        empty.orElseGet(String::new);
////        Object o = empty.orElse(new String());
////        Optional<Object> empty = Optional.ofNullable(null);
////        empty.orElseThrow(() -> {
////            return new RuntimeException();
////        });
////        Stream.of(1).
////        empty.orElseThrow(()->{
////            return new TypeConverterException(ResultEnum.CONVERTER_EXCEPTION);
////        });
////        empty.ifPresent(System.out::print);
////        System.out.println(o.getClass());
////        System.out.println(empty.isPresent());
//    }
    @Test
    public void test2() {
        Optional.ofNullable(null).ifPresent(System.out::print);
    }
    @Test
    public void test3(){
        assert false;
        assert 1==0:new RuntimeException("--");
        System.out.println("dd");
    }
}
