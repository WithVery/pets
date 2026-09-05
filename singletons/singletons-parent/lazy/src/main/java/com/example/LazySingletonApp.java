package com.example;

import com.example.model.LazySingleton;

public class LazySingletonApp {
    public static void main(String[] args) {
        LazySingleton singleton = LazySingleton.getInstance();
        System.out.println(singleton.saySomething());
    }
}
