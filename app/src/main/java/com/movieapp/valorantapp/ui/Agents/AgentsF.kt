package com.movieapp.valorantapp.ui.Agents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.movieapp.valorantapp.databinding.FragmentAgentsBinding
import com.movieapp.valorantapp.ui.Agents.adapter.AgentsAdapter
import com.movieapp.valorantapp.ui.Agents.viewmodel.AgentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsF : Fragment() {

    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!

    private val agentsViewModel: AgentsViewModel by viewModels()
    private lateinit var adapter : AgentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialMethod()
        observeData()
    }

    private fun initialMethod(){
        adapter= AgentsAdapter()
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
    }

    private fun observeData(){
        agentsViewModel.agentsData.observe(viewLifecycleOwner) { agents ->
            if(agents.data.isNotEmpty()){
                binding.recyclerView.visibility=View.VISIBLE
                binding.progressBar.visibility=View.GONE
                binding.errorText.visibility=View.GONE
                binding.errorText.visibility=View.GONE
                adapter.setAgentData(agents.data)
            }
        }

        agentsViewModel.progresBar.observe(viewLifecycleOwner) { showProgressBar ->
            showProgressBar?.let {
                if(it){
                    binding.progressBar.visibility=View.VISIBLE
                    binding.errorText.visibility=View.GONE
                    binding.recyclerView.visibility=View.GONE
                }
                else{
                    binding.progressBar.visibility=View.GONE
                    binding.recyclerView.visibility=View.VISIBLE
                }

            }
        }

        agentsViewModel.errorMessage.observe(viewLifecycleOwner) { showError ->
            showError?.let {
                if(it){
                    binding.errorText.visibility=View.VISIBLE
                }else{
                    binding.errorText.visibility=View.GONE
                }
            }
        }
    }


}