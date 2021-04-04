package com.example.endmillcalculator.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.lifecycle.Observer
import com.example.endmillcalculator.R
import com.example.endmillcalculator.databinding.HelixFragmentBinding
import com.example.endmillcalculator.models.HelixModel

class HelixFragment : Fragment(R.layout.helix_fragment) {

    companion object {
        fun newInstance() = HelixFragment()
    }

    private lateinit var viewModel: HelixViewModel
    private var _binding: HelixFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HelixFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(HelixViewModel::class.java)
        viewModel.bindState(this::onRestore)
        binding.calculateButton.setOnClickListener(this::onCalculate)
        binding.helixToLeadSwitch.setOnCheckedChangeListener { btn, isChecked ->
                if(isChecked){

                    onLead(view = btn)
                }else{
                    onHelix(view = btn)
                }
        }
        binding.toLeadLabel.setOnClickListener(this::onHelix)
        binding.toHelixLabel.setOnClickListener(this::onLead)
        viewModel.result.observe(viewLifecycleOwner, Observer {
            binding.result.text = it.toString()
        })

    }

    private fun onHelix(view: View):Unit{
        binding.helixToLeadSwitch.isChecked = false
        binding.helixInput.hint = "Enter helix"
    }
    private fun onLead(view: View){
        binding.helixToLeadSwitch.isChecked = true
        binding.helixInput.hint = "Enter lead"
    }
    private fun onCalculate(view: View):Unit{
        viewModel.calculate(model = HelixModel(
                toolOd = binding.helixDiameterInput.text.toString(),
                helixOrLead = binding.helixInput.text.toString(),
                isChecked = binding.helixToLeadSwitch.isChecked
        ))
        hideKeyboard()
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun hideKeyboard(){
        val inputService: InputMethodManager? = context?.getSystemService(InputMethodManager::class.java)
        inputService?.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
    private fun onRestore(model:HelixModel):Unit{
        binding.helixDiameterInput.text.append(model.toolOd)
        binding.helixInput.text.append(model.helixOrLead)
        binding.helixToLeadSwitch.isChecked = model.isChecked
    }
    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.setState(newState = HelixModel(
                toolOd = binding.helixDiameterInput.text.toString(),
                helixOrLead = binding.helixInput.text.toString(),
                isChecked = binding.helixToLeadSwitch.isChecked
        ))
        super.onSaveInstanceState(outState)
    }



}