package com.example.vagrant.autovalueapp;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class PersonTest {
    @Test
    public void test_person_value() {
        Person p = Person.builder().setAge(10)
                .setFirstName("Micheal")
                .setLastName("Jordan")
                .setGender(Person.GENDER.MALE).build();

        assertEquals(p.firstName(),"Micheal");
        assertEquals(p.lastName(), "Jordan");
        assertNull(p.middleName());
        assertEquals(p.gender(),Person.GENDER.MALE);
    }

    @Test
    public void test_person_empty_middle_value() {
        Person p = Person.creator().setAge(10)
                .setFirstName("Micheal")
                .setLastName("Jordan")
                .setGender(Person.GENDER.MALE).build();

        assertEquals("Micheal", p.firstName());
        assertEquals("Jordan",p.lastName());
        assertEquals("", p.middleName());
        assertEquals(Person.GENDER.MALE, p.gender());
    }
}
