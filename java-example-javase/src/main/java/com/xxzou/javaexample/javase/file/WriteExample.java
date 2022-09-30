package com.xxzou.javaexample.javase.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author zxx
 * @date 2022/9/29 11:21
 */
public class WriteExample {

    public static void main(String[] args) {

    }

    public static void writeByte(byte[] bytes, File dest){
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))){
            bos.write(bytes);
            bos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeByte(byte[] bytes, Path path) throws IOException {
        Files.write(path, bytes);
    }

}
