package com.example.ptt2vip.databindingtutorial;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ptt2vip.databindingtutorial.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static User myUser = new User("Tan", "Chun Mun");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binder.setUser(myUser);
        /*ActivityMainBinding binder = ActivityMainBinding.inflate(getLayoutInflater(), container, attachToContainer);
        View view = binding.getRoot();
        TemperatureData temperatureData = // your data is created here
                binding.setTemp(temperatureData);*/
    }
}
