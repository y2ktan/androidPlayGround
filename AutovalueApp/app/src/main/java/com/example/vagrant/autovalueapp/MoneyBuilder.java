package com.example.vagrant.autovalueapp;
import com.google.auto.value.AutoValue;

@AutoValue
abstract class MoneyBuilder {
    abstract String getCurrency();
    abstract long getAmount();

    static Builder builder() {
        return new AutoValue_MoneyBuilder.Builder();
    }


    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setCurrency(String currency);
        abstract Builder setAmount(long amount);
        abstract MoneyBuilder build();
    }
}