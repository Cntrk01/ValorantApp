package com.movieapp.valorantapp.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.movieapp.valorantapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButton()
    }

    private fun clickButton(){
        binding.agents.setOnClickListener {
            val nav=HomeFragmentDirections.actionNavHomeToNavAgents()
            findNavController().navigate(nav)
        }
        binding.buddies.setOnClickListener {
            val nav=HomeFragmentDirections.actionNavHomeToNavBuddies()
            findNavController().navigate(nav)
        }
        binding.bundle.setOnClickListener {
            val nav=HomeFragmentDirections.actionNavHomeToNavBundle()
            findNavController().navigate(nav)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}