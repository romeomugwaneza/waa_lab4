package com.waa.lab4.helper;

import java.util.Random;

public class RandomLongGenerator {
    public static long getLongId(){

        Random random = new Random();

        long minValue = 4;
        long maxValue = Long.MAX_VALUE;

        return minValue + (long) (random.nextDouble() * (maxValue - minValue + 1));

    }
}
