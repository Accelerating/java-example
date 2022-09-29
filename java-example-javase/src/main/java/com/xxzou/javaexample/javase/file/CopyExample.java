package com.xxzou.javaexample.javase.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zxx
 * @date 2022/9/29 11:14
 */
public class CopyExample {

    public static void main(String[] args) throws IOException {

        String srcFile = "D:/temp";
        String destFile = "D:/temp2";
        copy(Paths.get(srcFile), Paths.get(destFile));


    }

    private static void copy(Path src, Path dest){
        Path absoluteSrcPath = src.toAbsolutePath();
        Path absoluteDestPath = dest.toAbsolutePath();
        try{
            Files.copy(absoluteSrcPath, absoluteDestPath);
            if(Files.isDirectory(absoluteSrcPath)){
                Files.newDirectoryStream(absoluteSrcPath).forEach(p -> {
                    String filename = p.getFileName().toString();
                    copy(p, Paths.get(absoluteDestPath.toString(), filename));
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
