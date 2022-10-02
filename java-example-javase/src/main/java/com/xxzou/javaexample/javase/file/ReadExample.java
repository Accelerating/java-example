package com.xxzou.javaexample.javase.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author zxx
 * @date 2022/9/29 11:21
 */
public class ReadExample {

    public static void main(String[] args) {
        String s = readToString(new File("D:/security/ps.txt"));
        System.out.println(s);
    }

    public static byte[] readToBytes(File file){
        byte[] data = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))){
            byte[] buf = new byte[1024];

            int readBytes = 0;
            while((readBytes = in.read(buf)) != -1){
                out.write(buf, 0, readBytes);
            }
            data = out.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    public static byte[] readToBytes(Path path) throws IOException{
        return Files.readAllBytes(path);
    }

    public static String readToString(File file){
        StringBuilder builder = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = null;
            while((line = br.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String readToString(Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            builder.append(line);
        }
        return builder.toString();
    }

}
