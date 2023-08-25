package com.movieapp.valorantapp.ui.Agents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.movieapp.valorantapp.R
import com.movieapp.valorantapp.agents_buddies_dataclass.Agents
import com.movieapp.valorantapp.databinding.RowBinding
import com.movieapp.valorantapp.ui.Agents.AgentsFDirections

class AgentsAdapter : RecyclerView.Adapter<AgentsAdapter.AgentsViewHolder>() {

    private var list = arrayOf<Agents>()

    fun setAgentData(data: Array<Agents>){
        this.list= data
        notifyDataSetChanged()
    }

    inner class AgentsViewHolder(val binding:RowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
       val inf=LayoutInflater.from(parent.context)
       val view=DataBindingUtil.inflate<RowBinding>(inf, R.layout.row,parent,false)
       return AgentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        val poz=list[position]
        holder.binding.agent=poz

        holder.itemView.setOnClickListener {
            val intent=AgentsFDirections.actionNavAgentsToAgentsDetail(poz)
            Navigation.findNavController(holder.itemView).navigate(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}