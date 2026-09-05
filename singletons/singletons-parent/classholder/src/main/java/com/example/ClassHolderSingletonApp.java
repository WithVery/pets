package com.example;

import com.example.model.ClassHolderSingleton;

public class ClassHolderSingletonApp {
    public static void main(String[] args) {
        ClassHolderSingleton singleton = ClassHolderSingleton.getInstance();
        System.out.println(singleton.saySomething());
    }
}
