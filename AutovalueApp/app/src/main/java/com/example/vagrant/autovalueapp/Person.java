package com.example.vagrant.autovalueapp;

import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Person {
    enum GENDER{
        MALE,
        FEMALE
    }
    abstract String firstName();
    abstract String lastName();
    abstract int age();
    abstract GENDER gender();
    @Nullable
    abstract String middleName();

    static Person.Builder builder() {
        return new AutoValue_Person.Builder();
    }

    static Builder creator() {
        return builder()
                .setMiddleName("");
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Person.Builder setFirstName(String firstName);
        abstract Person.Builder setLastName(String lastName);
        abstract Person.Builder setAge(int age);
        abstract Person.Builder setGender(GENDER gender);
        abstract Person.Builder setMiddleName(String middleName);
        abstract Person build();
    }
}
