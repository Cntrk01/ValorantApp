package com.movieapp.valorantapp.ui.Buddies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.valorantapp.R
import com.movieapp.valorantapp.agents_buddies_dataclass.Buddies
import com.movieapp.valorantapp.databinding.BuddiesRowBinding

class BuddiesAdapter : RecyclerView.Adapter<BuddiesAdapter.BuddiesViewHolder>() {

    private var list = arrayOf<Buddies>()

    fun setBuddiesData(data: Array<Buddies>){
        this.list= data
        notifyDataSetChanged()
    }

    inner class BuddiesViewHolder(val binding :BuddiesRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuddiesViewHolder {
        val inf= LayoutInflater.from(parent.context)
        val view= DataBindingUtil.inflate<BuddiesRowBinding>(inf, R.layout.buddies_row,parent,false)
        return BuddiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuddiesViewHolder, position: Int) {
        holder.binding.buddies=list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}