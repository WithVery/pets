package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.DoubleCheckedLockingSingleton;
import org.junit.jupiter.api.Test;

public class DoubleCheckedLockingSingletonAppTest {

    @Test
    public void shouldAnswerWithTrue() {
        DoubleCheckedLockingSingleton singleton = DoubleCheckedLockingSingleton.getInstance();
        assertTrue(singleton.saySomething().equals("something"));
    }
}
