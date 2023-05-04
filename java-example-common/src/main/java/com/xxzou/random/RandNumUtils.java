package com.xxzou.random;

import java.util.concurrent.ThreadLocalRandom;

public class RandNumUtils {

    public static int getRandInt(int start, int end){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(start, end);
    }

    public static long getRandLong(int start, int end){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(start, end);
    }
}
