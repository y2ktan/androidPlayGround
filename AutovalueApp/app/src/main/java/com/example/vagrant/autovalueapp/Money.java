package com.example.vagrant.autovalueapp;
import com.google.auto.value.AutoValue;

@AutoValue
//Basic usage of AutoValue using a static factory method as our public creation API.
abstract class Money {
    abstract String getCurrency();
    abstract long getAmount();

    static Money create(String currency, long amount) {
        return new AutoValue_Money(currency, amount);
    }
}
