package com.xxzou.javaexample.javase.syntax;

public class FinallyExample {

    public static void main(String[] args) {
        System.out.println(testFinally());
    }

    public static int testFinally(){

        int a = 10;
        try{
            a = 20;
            return a;
        }finally {
            a = 30;
        }
    }

}
