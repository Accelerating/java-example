package com.xxzou.javaexample.feature.java12;

public class SwitchExpression {

    public static void main(String[] args) {
        int level = 3;
        beforeJava12(level);
        afterJava12(level);
    }

    public static void beforeJava12(int level){
        String result = "";
        switch(level){
            case 10:
            case 9:
            case 8:
                result = "good";
                break;
            case 7:
            case 6:
                result = "normal";
                break;
            default:
                result = "bad";
        }

        System.out.println("result:" + result);
    }

    public static void afterJava12(int level){
        //不用写break，但是default是必须的
        String result = switch (level){
            case 10, 9, 8 -> "good";
            case 7, 6 -> "normal";
            default -> "bad";
        };
        System.out.println(result);
    }


    public static void yieldKeyWord(int level){
        //有可能不仅仅秩序返回一个值可能还要做一些其他的事情

        String result = switch (level){
            case 10,9,8:
                System.out.println("match good");
                yield "good";
            case 7,6:
                System.out.println("match normal");
                yield "normal";
            default:
                System.out.println("match bad");
                yield "bad";
        };
        System.out.println(result);

    }

}
