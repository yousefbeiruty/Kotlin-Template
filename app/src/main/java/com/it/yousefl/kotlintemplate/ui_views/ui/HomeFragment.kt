package com.it.yousefl.kotlintemplate.ui_views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.it.yousefl.kotlintemplate.R
import com.it.yousefl.kotlintemplate.databinding.FragmentHomeBinding
import com.it.yousefl.kotlintemplate.utils.AuthResource
import com.it.yousefl.kotlintemplate.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Observer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private  val TAG = "HomeFragment"
lateinit var binding:FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    @ExperimentalStdlibApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)


        subscribeObservers()
        viewModel.setStateEvent(MainStateevent.GetBlogsEvent)

        return binding.root
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState.status){
                Resource.Status.LOADING -> {
                    //      showLoading()
                    Log.d(TAG, "onChanged: LOADING")
                }
                Resource.Status.ERROR -> {
                    //    hideLoading()
                    Log.d(TAG, "onChanged: ERROR")
                }
            }
        })
    }
}