package com.example.endmillcalculator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.endmillcalculator.R
import com.example.endmillcalculator.databinding.FragmentRootBinding
import com.example.endmillcalculator.recyclerModel.CardAdapter


class RootFragment : Fragment(R.layout.fragment_root) {
    companion object{
        fun newInstance() = RootFragment()
    }

    private var _binding:FragmentRootBinding? =null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRootBinding.bind(view)
        val grid = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.listOfString.layoutManager = LinearLayoutManager(context)
        binding.listOfString.adapter = CardAdapter()
        binding.listOfString.layoutManager = grid


    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



}