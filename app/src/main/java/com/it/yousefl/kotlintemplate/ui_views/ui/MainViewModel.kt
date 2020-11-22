package com.it.yousefl.kotlintemplate.ui_views.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.it.yousefl.kotlintemplate.models.Orders
import com.it.yousefl.kotlintemplate.repository.MainRepository
import com.it.yousefl.kotlintemplate.utils.Resource

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun orders(id:Int) = repository.getOrders(id)

}