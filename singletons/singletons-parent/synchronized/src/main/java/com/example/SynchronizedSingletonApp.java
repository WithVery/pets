package com.example;

import com.example.model.SynchronizedSingleton;

public class SynchronizedSingletonApp {
    public static void main(String[] args) {
        SynchronizedSingleton singleton = SynchronizedSingleton.getInstance();
        System.out.println(singleton.saySomething());
    }
}
