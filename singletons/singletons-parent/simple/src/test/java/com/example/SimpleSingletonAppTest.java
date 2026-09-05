package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.SimpleSingleton;
import org.junit.jupiter.api.Test;

public class SimpleSingletonAppTest {

    @Test
    public void singletonSaidSomething() {
        assertTrue(SimpleSingleton.saySomething().equals("something"));
    }
}
