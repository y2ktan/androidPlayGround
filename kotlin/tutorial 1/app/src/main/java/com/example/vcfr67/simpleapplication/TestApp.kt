package com.example.vcfr67.simpleapplication

import android.app.PendingIntent.getActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.vcfr67.simpleapplication.R.id.list_recycler_view
import com.example.vcfr67.simpleapplication.adapters.CustomerAdapter
import com.example.vcfr67.simpleapplication.model.Customer
import com.example.vcfr67.simpleapplication.viewmodel.AppVM
import com.example.vcfr67.simpleapplication.viewmodel.AppViewModelFactory
import kotlinx.android.synthetic.main.app.*

class TestApp : AppCompatActivity() {
    private lateinit var appViewModel: AppVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app)
        list_recycler_view.layoutManager =  LinearLayoutManager(applicationContext)
        appViewModel = ViewModelProviders.of(this, AppViewModelFactory(application)).get(AppVM::class.java).apply {
            updateCustomerList()
            getCustomerList.observe(this@TestApp , Observer { singleEvent ->
                singleEvent?.getEventIfNotHandled()?.let { customers ->
                    list_recycler_view.adapter = CustomerAdapter(customers, applicationContext)
                }
            })
        }

//        obtainResult.setOnClickListener {
//            appViewModel.updateCustomerList()
//        }

    }


}
