package com.xxzou.javaexample.feature.java9;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactory {

    public static void main(String[] args) {

        //这些集合都是不可以修改的
        List<Object> list = List.of("123", "abc");
        Set<Object> set = Set.of("123", "abc");


        Map<Object, Object> map = Map.of("k1", "v1", "k2", "v2");
        Map.ofEntries(Map.entry("k1", "v1"), Map.entry("k2", "v2"));

    }

}
