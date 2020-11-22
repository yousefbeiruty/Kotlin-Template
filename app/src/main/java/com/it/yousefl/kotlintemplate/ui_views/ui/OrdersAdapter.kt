package com.it.yousefl.kotlintemplate.ui_views.ui

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.it.yousefl.kotlintemplate.databinding.ItemOrderBinding
import com.it.yousefl.kotlintemplate.room.OrderEntity


class OrdersAdapter( val layout_id:Int,var context: Context, var ordeers: List<OrderEntity>) :
    RecyclerView.Adapter<OrdersAdapter.Holder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val binding: ItemOrderBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(context),R.layout.item_order, parent, false
////        )
//        val inflater=LayoutInflater.from(context)
//        val binding:ItemOrderBinding = DataBindingUtil.inflate(inflater,R.layout.item_order, parent, false)
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemOrderBinding>(layoutInflater, layout_id, parent, false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.tvAddress.text = ordeers.get(position).address
        holder.binding.tvBillNumber.text = "${ordeers.get(position).billno}"
        holder.binding.tvDate.text = ordeers.get(position).billdate
        holder.binding.tvPhone.text = ordeers.get(position).mobile
    }

    override fun getItemCount(): Int {
        return ordeers.size
    }

    class Holder(var binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}