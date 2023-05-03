package com.xxzou.javaexample.feature.java9;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TryWithResource {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("pom.xml");

        //在jdk9之前，必须定义在try那个括号里面
        try(InputStream in = Files.newInputStream(path)){
            int available = in.available();
            System.out.println(available);
        }


        //在jdk9之后，可以定义在try之前
        InputStream in = Files.newInputStream(path);
        try(in){
            int available = in.available();
            System.out.println(available);
        }

    }




}
