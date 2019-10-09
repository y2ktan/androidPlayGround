package com.example.vcfr67.androidtrainning;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.vcfr67.androidtrainning.databinding.ListItemBinding;
import com.example.vcfr67.androidtrainning.models.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VCFR67 on 2/7/2018.
 */

public class DataItemAdapter extends BaseAdapter {
    List<DataItem> mDataItems;
    LayoutInflater mInflater;

    public DataItemAdapter(@NonNull ArrayList<DataItem> objects) {
        mDataItems = objects;

    }
    @Override
    public int getCount() {
        return mDataItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView, @NonNull ViewGroup parent) {

        if (mInflater == null) {
            mInflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        ListItemBinding binding = DataBindingUtil.getBinding(convertView);
        if(binding == null) {
            binding = DataBindingUtil.inflate(
                    mInflater, R.layout.list_item, parent, false);
        }
        binding.setInfo(mDataItems.get(position));

        return binding.getRoot();
    }
}
