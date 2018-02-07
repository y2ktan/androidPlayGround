package com.example.vcfr67.androidtrainning;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vcfr67.androidtrainning.databinding.ActivityMainBinding;
import com.example.vcfr67.androidtrainning.sample.SampleDataProvider;


public class MainActivity extends AppCompatActivity {
    private SampleDataProvider SampleDataProviderObj =  new SampleDataProvider();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);

        binding.setInfos(SampleDataProviderObj);
    }
}
