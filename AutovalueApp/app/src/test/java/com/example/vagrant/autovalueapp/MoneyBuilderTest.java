package com.example.vagrant.autovalueapp;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class MoneyBuilderTest {
    @Test
    public void test_currency_value() {
        MoneyBuilder m = MoneyBuilder.builder().setAmount(1000).setCurrency("USD").build();
        assertEquals(m.getAmount(), 1000);
        assertEquals(m.getCurrency(), "USD");
    }

    @Test
    public void test_money_instance() {
        MoneyBuilder m1 = MoneyBuilder.builder().setAmount(1000).setCurrency("USD").build();
        MoneyBuilder m2 = MoneyBuilder.builder().setAmount(1000).setCurrency("USD").build();
        assertEquals(m1,m2);
    }

    @Test
    public void test_money_instance_diff() {
        MoneyBuilder m1 = MoneyBuilder.builder().setAmount(1000).setCurrency("USD").build();
        MoneyBuilder m2 = MoneyBuilder.builder().setAmount(1000).setCurrency("PLN").build();
        assertFalse(m1.equals(m2));
    }
}

