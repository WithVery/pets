package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.SynchronizedSingleton;
import org.junit.jupiter.api.Test;

public class SynchronizedSingletonAppTest {
    @Test
    public void shouldAnswerWithTrue() {
        SynchronizedSingleton singleton = SynchronizedSingleton.getInstance();
        assertTrue(singleton.saySomething().equals("something"));
    }
}
