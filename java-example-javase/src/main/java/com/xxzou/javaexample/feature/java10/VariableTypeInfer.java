package com.xxzou.javaexample.feature.java10;

public class VariableTypeInfer {

    //编译不通过
//    var field = "hello";

    public static void main(String[] args) {

        //只能用在局部变量
        var a = 1;
        var b = "string";

        System.out.println(a);
        System.out.println(b);

    }

}
