package com.example.vcfr67.simpleapplication.viewmodel


import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class AppViewModelFactory(private val app:Application): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppVM(app) as T
    }

}