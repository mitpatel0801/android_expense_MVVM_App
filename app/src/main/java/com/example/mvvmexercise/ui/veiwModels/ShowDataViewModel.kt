package com.example.mvvmexercise.ui.veiwModels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmexercise.Expense
import com.google.gson.GsonBuilder

class ShowDataViewModel(activity: Activity) : ViewModel() {

    private var _totalExpenses: MutableLiveData<MutableList<Expense>> =
        MutableLiveData<MutableList<Expense>>(mutableListOf<Expense>())

    val totalExpenses: LiveData<MutableList<Expense>> get() = _totalExpenses

    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

    init {
        fetchData()
    }

    private fun fetchData() {
        val mySetValues = sharedPref.getStringSet("MY_VALUES", null)
        val gson = GsonBuilder().create()

        val expenseData = mutableListOf<Expense>()
        mySetValues?.forEach {
            expenseData.add(gson.fromJson(it, Expense::class.java))
        }

        _totalExpenses.value = expenseData
    }


    fun addItem(expense: Expense) {

        val newExpenses = mutableListOf<Expense>()
        newExpenses.add(expense)

        _totalExpenses.value?.let { newExpenses.addAll(it) }
        _totalExpenses.value?.clear()

        val gson = GsonBuilder().create();
        val gsonStringSet = newExpenses.map { gson.toJson(it, Expense::class.java) }.toSet()

        with(sharedPref.edit()) {
            putStringSet("MY_VALUES", gsonStringSet)
            apply()
        }

        _totalExpenses.value = newExpenses

    }

    fun updateData() {

        val gson = GsonBuilder().create()
        val gsonStringSet = _totalExpenses.value?.map { gson.toJson(it, Expense::class.java) }?.toSet()

        with(sharedPref.edit()) {
            putStringSet("MY_VALUES", gsonStringSet)
            apply()
        }
    }
}