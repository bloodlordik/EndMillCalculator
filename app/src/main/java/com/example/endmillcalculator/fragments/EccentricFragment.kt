package com.example.endmillcalculator.fragments

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.endmillcalculator.R
import com.example.endmillcalculator.databinding.EccenticFragmentBinding

class EccentricFragment : Fragment(R.layout.eccentic_fragment) {

    companion object {
        fun newInstance() = EccentricFragment()
    }
    private val inputWatcher = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            viewModel.setInputHelix(text = binding.helixAngelInput.text.toString())
            viewModel.setInputLand(text = binding.helixLandInput.text.toString())
        }

    }
    private var _binding:EccenticFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EccenticViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EccenticFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(EccenticViewModel::class.java)
        binding.eccentricCalculate.setOnClickListener(this::onCalculate)
        binding.helixAngelInput.addTextChangedListener(inputWatcher)
        binding.helixLandInput.addTextChangedListener(inputWatcher)
        viewModel.onResult.observe(viewLifecycleOwner, Observer {
            binding.result.text = Editable.Factory().newEditable(it.toString())
        })
        onRestore()


    }
    private fun onCalculate(view:View){
        viewModel.calculate()
        hideKeyboard()
    }
    private fun onRestore(){
        binding.helixAngelInput.text = Editable.Factory().newEditable(viewModel.inputText)
        binding.helixLandInput.text = Editable.Factory().newEditable(viewModel.inputLand)


    }
    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.setInputHelix(text = binding.helixAngelInput.text.toString())
        viewModel.setInputLand(text = binding.helixLandInput.text.toString())
        super.onSaveInstanceState(outState)

    }
    fun hideKeyboard(){
        val inputService = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputService.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}