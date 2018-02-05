package com.example.ptt2vip.databindingtutorial;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by ptt2vip on 1/24/2018.
 */

public class User extends BaseObservable {
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(CharSequence s, int start, int before, int count) {

        this.firstName = s.toString();
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;

    }

    public void setLastName(CharSequence s, int start, int before, int count) {
        this.lastName = s.toString();
        notifyPropertyChanged(BR.lastName);
    }

    private String firstName;
    private String lastName;

    public class UserInfoChange
    {

    }
}
