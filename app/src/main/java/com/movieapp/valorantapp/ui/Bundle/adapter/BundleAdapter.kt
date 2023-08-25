package com.movieapp.valorantapp.ui.Bundle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.valorantapp.R
import com.movieapp.valorantapp.agents_buddies_dataclass.Agents
import com.movieapp.valorantapp.bundle.Bundle
import com.movieapp.valorantapp.databinding.BundleRowBinding
import com.movieapp.valorantapp.databinding.RowBinding
import com.movieapp.valorantapp.ui.Agents.adapter.AgentsAdapter

class BundleAdapter : RecyclerView.Adapter<BundleAdapter.BundleViewHolder>() {

    private var list = arrayOf<Bundle>()

    fun setBundleData(data: Array<Bundle>){
        this.list= data
        notifyDataSetChanged()
    }

    inner class BundleViewHolder(val binding: BundleRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BundleViewHolder {
        val inf= LayoutInflater.from(parent.context)
        val view= DataBindingUtil.inflate<BundleRowBinding>(inf, R.layout.bundle_row,parent,false)
        return BundleViewHolder(view)
    }

    override fun onBindViewHolder(holder: BundleViewHolder, position: Int) {
        val poz=list[position]
        holder.binding.bundle=poz

//        holder.binding.agentsUid.text=poz.uuid
//        holder.binding.agentsDesc.text=poz.description
//        holder.binding.agentsDevName.text=poz.developerName
//        holder.binding.agentsDispName.text=poz.displayName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}