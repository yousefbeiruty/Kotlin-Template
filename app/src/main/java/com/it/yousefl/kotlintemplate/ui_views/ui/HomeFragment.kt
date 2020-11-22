package com.it.yousefl.kotlintemplate.ui_views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.it.yousefl.kotlintemplate.R
import com.it.yousefl.kotlintemplate.databinding.FragmentHomeBinding
import com.it.yousefl.kotlintemplate.room.OrderEntity
import com.it.yousefl.kotlintemplate.utils.Constants
import com.it.yousefl.kotlintemplate.utils.Resource
import com.it.yousefl.kotlintemplate.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private  val TAG = "HomeFragment"
    lateinit var binding:FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: OrdersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }
    private fun setupObservers() {
        viewModel.orders(Utils.getValue(activity,Constants.ID,0)).observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        Timber.d("setupObservers  SUCCESS ")
                        Log.d(TAG, "setupObservers: SUCCESS")

                        Log.d(TAG, "setupObservers: data= "+Utils.toJson(it.data))
//                    binding.progressBar.visibility = View.GONE
                        if (!it.data.isNullOrEmpty())
                            setRc(it.data)
                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING ->
                        Timber.d("setupObservers  LOADING ")
//                    binding.progressBar.visibility = View.VISIBLE
                }
            })
    }

    fun setRc(data:List<OrderEntity>){
        adapter= activity?.let { OrdersAdapter(R.layout.item_order,it,data) }!!
        binding.rc.adapter=adapter
        binding.rc.layoutManager=LinearLayoutManager(activity)
        binding.rc.setHasFixedSize(true)

    }
}

