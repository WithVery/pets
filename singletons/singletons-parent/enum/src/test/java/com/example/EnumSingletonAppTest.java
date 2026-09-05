package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.EnumSingleton;
import org.junit.jupiter.api.Test;

public class EnumSingletonAppTest {

    @Test
    public void shouldAnswerWithTrue() {
        EnumSingleton singleton = EnumSingleton.INSTANCE;
        assertTrue(singleton.saySomething().equals("something"));
    }
}
