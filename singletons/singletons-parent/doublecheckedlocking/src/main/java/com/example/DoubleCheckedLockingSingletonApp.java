package com.example;

import com.example.model.DoubleCheckedLockingSingleton;

public class DoubleCheckedLockingSingletonApp {
    public static void main(String[] args) {
        DoubleCheckedLockingSingleton singleton = DoubleCheckedLockingSingleton.getInstance();
        System.out.println(singleton.saySomething());
    }
}
