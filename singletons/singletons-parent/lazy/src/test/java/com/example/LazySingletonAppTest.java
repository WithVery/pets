package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.LazySingleton;
import org.junit.jupiter.api.Test;

public class LazySingletonAppTest {

    @Test
    public void shouldAnswerWithTrue() {
        LazySingleton singleton = LazySingleton.getInstance();
        assertTrue(singleton.saySomething().equals("something"));
    }
}
