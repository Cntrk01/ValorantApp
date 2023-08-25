package com.movieapp.valorantapp.ui.Bundle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.movieapp.valorantapp.R
import com.movieapp.valorantapp.databinding.FragmentAgentsBinding
import com.movieapp.valorantapp.databinding.FragmentBundleBinding
import com.movieapp.valorantapp.ui.Agents.adapter.AgentsAdapter
import com.movieapp.valorantapp.ui.Agents.viewmodel.AgentsViewModel
import com.movieapp.valorantapp.ui.Bundle.adapter.BundleAdapter
import com.movieapp.valorantapp.ui.Bundle.viewmodel.BundleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BundleF : Fragment() {

    private var _binding: FragmentBundleBinding? = null
    private val binding get() = _binding!!

    private val bundleViewModel: BundleViewModel by viewModels()
    private lateinit var adapter : BundleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBundleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialMethod()
        observeData()
    }

    private fun initialMethod(){
        adapter= BundleAdapter()
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
    }

    private fun observeData(){
        bundleViewModel.bundlesData.observe(viewLifecycleOwner) { agents ->
            if(agents.data.isNotEmpty()){
                binding.recyclerView.visibility=View.VISIBLE
                binding.progressBar.visibility=View.GONE
                binding.errorText.visibility=View.GONE
                binding.errorText.visibility=View.GONE
                adapter.setBundleData(agents.data)
            }
        }

        bundleViewModel.progresBar.observe(viewLifecycleOwner) { showProgressBar ->
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

        bundleViewModel.errorMessage.observe(viewLifecycleOwner) { showError ->
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