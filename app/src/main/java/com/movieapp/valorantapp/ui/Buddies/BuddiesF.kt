package com.movieapp.valorantapp.ui.Buddies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.movieapp.valorantapp.databinding.FragmentBuddiesBinding
import com.movieapp.valorantapp.ui.Buddies.adapter.BuddiesAdapter
import com.movieapp.valorantapp.ui.Buddies.viewmodel.BuddiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuddiesF : Fragment() {
    private var _binding: FragmentBuddiesBinding? = null
    private val binding get() = _binding!!

    private val agentsViewModel: BuddiesViewModel by viewModels()
    private lateinit var adapter : BuddiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuddiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialMethod()
        observeData()
    }

    private fun initialMethod(){
        adapter= BuddiesAdapter()
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
    }

    private fun observeData(){
        agentsViewModel.buddiesData.observe(viewLifecycleOwner) { buddies ->
            if(buddies.data.isNotEmpty()){
                binding.recyclerView.visibility=View.VISIBLE
                binding.progressBar.visibility=View.GONE
                binding.errorText.visibility=View.GONE
                binding.errorText.visibility=View.GONE
                adapter.setBuddiesData(buddies.data)
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