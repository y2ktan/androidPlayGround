package com.example.vagrant.autovalueapp;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class MoneyTest {
    @Test
    public void test_currency_value() {
        Money m = Money.create("USD", 10000);
        assertEquals(10000, m.getAmount());
        assertEquals("USD", m.getCurrency());
    }

    @Test
    public void test_money_instance() {
        Money m1 = Money.create("USD", 10000);
        Money m2 = Money.create("USD", 10000);
        assertEquals(m1,m2);
    }

    @Test
    public void test_money_instance_diff() {
        Money m1 = Money.create("PLN", 5000);
        Money m2 = Money.create("USD", 5000);
        assertFalse(m1.equals(m2));
    }
}

