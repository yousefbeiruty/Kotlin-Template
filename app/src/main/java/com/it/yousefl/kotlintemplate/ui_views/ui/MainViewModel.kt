package com.it.yousefl.kotlintemplate.ui_views.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.it.yousefl.kotlintemplate.models.Orders
import com.it.yousefl.kotlintemplate.repository.MainRepository
import com.it.yousefl.kotlintemplate.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainReposetory: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _dataState: MutableLiveData<Resource<List<Orders>>> = MutableLiveData()

    val dataState: LiveData<Resource<List<Orders>>> get() = _dataState
    @ExperimentalStdlibApi
    fun setStateEvent(mainStateevent: MainStateevent){
        viewModelScope.launch {
            when(mainStateevent){
                is MainStateevent.GetBlogsEvent ->{
                    mainReposetory.getBlogs()
                        .onEach{dataState->
                            _dataState.value=dataState.data

                        }.launchIn(viewModelScope)
                }
                is MainStateevent.None->{
                    //who cares
                }
            }
        }
    }

}
sealed class MainStateevent{

    object GetBlogsEvent:MainStateevent()

    object None:MainStateevent()
}