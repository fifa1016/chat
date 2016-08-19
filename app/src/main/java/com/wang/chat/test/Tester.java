package com.wang.chat.test;

/**
 * Created by wang on 16-8-11.
 */
public class Tester {
    private static Tester ourInstance = new Tester();

    public static Tester getInstance() {
        return ourInstance;
    }

    private Tester() {
    }
}
