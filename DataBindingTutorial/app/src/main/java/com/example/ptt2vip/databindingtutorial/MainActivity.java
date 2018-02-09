package com.example.ptt2vip.databindingtutorial;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ptt2vip.databindingtutorial.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static User myUser = new User("Tan", "Chun Mun");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binder.setUser(myUser);
        binder.executePendingBindings();

        Button OnSubmit = (Button) findViewById(R.id.btnSubmit);
        OnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Thread thread = new Thread("New Thread") {
                    public void run(){
                        String strLastName = myUser.getLastName();
                        String newStatement = "Hi "+ strLastName;
                        myUser.setLastName(newStatement,0,0,newStatement.length());
                    }
                };
                thread.start();

            }
        });
        /*ActivityMainBinding binder = ActivityMainBinding.inflate(getLayoutInflater(), container, attachToContainer);
        View view = binding.getRoot();
        TemperatureData temperatureData = // your data is created here
                binding.setTemp(temperatureData);*/
    }
}
