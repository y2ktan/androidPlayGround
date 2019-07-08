package com.example.vcfr67.simpleapplication.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.vcfr67.simpleapplication.model.Customer
import com.example.vcfr67.simpleapplication.tools.SingleEvent

class AppVM(app:Application): AndroidViewModel(app) {
    fun updateCustomerList() {
        _customerList.value = SingleEvent(customers)
    }

    private val customers = mutableListOf<Customer>()

    init {
        customers.add(Customer(1,"Tan", 18))
        customers.add(Customer(2,"Ang", 15))
        customers.add(Customer(3,"David Ley", 13))
    }

    val getCustomerList:LiveData<SingleEvent<List<Customer>>>
        get() = _customerList

    private val _customerList = MutableLiveData<SingleEvent<List<Customer>>>()
}