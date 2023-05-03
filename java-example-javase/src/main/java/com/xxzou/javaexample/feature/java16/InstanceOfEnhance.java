package com.xxzou.javaexample.feature.java16;

public class InstanceOfEnhance {

    public static void main(String[] args) {

        Object obj = new String("hello world");

        //before
        if(obj instanceof String){
            String str = (String) obj;
            System.out.println(str);
        }


        //after
        if(obj instanceof String str){
            System.out.println(str);
        }
    }

}
