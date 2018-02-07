package com.example.vcfr67.androidtrainning;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vcfr67.androidtrainning.models.DataItem;

/**
 * Created by VCFR67 on 2/7/2018.
 */

public class DataItemBinder {
    /*@BindingAdapter("bind:imageRes")
    public static void bindImage(ImageView view, int r) {

        view.setImageResource(r);
    }*/

    @BindingAdapter("bind:items")
    public static void bindList(ListView view,
                                ObservableArrayList<DataItem> list) {
        ListAdapter adapter = new DataItemAdapter(list);
        view.setAdapter(adapter);
    }
}
