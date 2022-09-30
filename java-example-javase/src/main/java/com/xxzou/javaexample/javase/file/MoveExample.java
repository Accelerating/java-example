package com.xxzou.javaexample.javase.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zxx
 * @date 2022/9/29 11:19
 */
public class MoveExample {

    public static void main(String[] args) throws IOException {

        Path src = Paths.get("d:/temp2");
        Path dest = Paths.get("d:/x/tmp2");
        Files.move(src, dest);

    }



}
