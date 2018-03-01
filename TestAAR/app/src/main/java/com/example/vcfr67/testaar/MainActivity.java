package com.example.vcfr67.testaar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.math.CalcManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        CalcManager manager = new CalcManager();
//        com.example.math.CalcManager manager =
//                new com.example.math.CalcManager();
        Integer sum = manager.Add(1,2);
        Toast.makeText(this.getApplicationContext(), sum.toString(), Toast.LENGTH_LONG).show();
    }
}
