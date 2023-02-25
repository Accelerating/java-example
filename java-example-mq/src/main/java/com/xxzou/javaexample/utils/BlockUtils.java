package com.xxzou.javaexample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlockUtils {


    public static String blockUntilInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for (;;){
            String s = blockUntilInput();
            System.out.println(s);
        }

    }

}
