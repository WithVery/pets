package com.example;

import com.example.model.EnumSingleton;

/**
 * Hello world!
 */
public class EnumSingletonApp {
    public static void main(String[] args) {
        EnumSingleton singleton = EnumSingleton.INSTANCE;
        System.out.println(singleton.saySomething());
    }
}
