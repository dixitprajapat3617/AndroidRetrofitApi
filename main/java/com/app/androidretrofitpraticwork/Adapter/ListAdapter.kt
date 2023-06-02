package com.app.androidretrofitpraticwork.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.androidretrofitpraticwork.databinding.ItemUiDesignBinding
import com.app.androidretrofitpraticwork.model.UserData
import com.bumptech.glide.Glide


class ListAdapter(var context: Context,var userlist:MutableList<UserData>):RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    lateinit var binding: ItemUiDesignBinding

    class MyViewHolder(var binding: ItemUiDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       binding= ItemUiDesignBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var name=userlist[position]

        holder.binding.tvCategory.text="${name.category}"
        holder.binding.tvTitle.text="${name.name}"
        holder.binding.tvDesc.text="${name.desc}"

        Glide
            .with(context)
            .load(name.imageUrl)
            .centerCrop()
            .into(holder.binding.imageView)




    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    fun setitem(userlist: MutableList<UserData>){
            this.userlist=userlist
            notifyDataSetChanged()
    }
}