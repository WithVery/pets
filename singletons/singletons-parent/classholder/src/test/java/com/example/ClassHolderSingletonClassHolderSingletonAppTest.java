package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.ClassHolderSingleton;
import org.junit.jupiter.api.Test;

public class ClassHolderSingletonClassHolderSingletonAppTest {

    @Test
    public void shouldAnswerWithTrue() {
        ClassHolderSingleton singleton = ClassHolderSingleton.getInstance();
        assertTrue(singleton.saySomething().equals("something"));
    }
}
