package com.xxzou.javaexample.feature.java17;

public class SealedClass {

    /**
     * 只有B类可以继承A类
     */
    sealed class A permits B{

    }

    /**
     * 继承了密封类的类，必须定义为final类或者密封类（sealed）或者非密封类（non-sealed）
     *
     */
    final class B extends A{

    }

}
