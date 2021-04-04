package com.example.endmillcalculator.fragments

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.example.endmillcalculator.R
import com.example.endmillcalculator.databinding.BackTaperFragmentBinding
import com.example.endmillcalculator.models.BackTaperModel

class BackTaperFragment : Fragment(R.layout.back_taper_fragment) {
    private var _binding:BackTaperFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = BackTaperFragment()
    }

    private lateinit var viewModel: BackTaperViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BackTaperFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(BackTaperViewModel::class.java)
        viewModel.getState(this::onRestore)
        viewModel.resultDiameter.observe(viewLifecycleOwner, Observer(this::onResultOd))
        viewModel.resultBackTaperAngel.observe(viewLifecycleOwner, Observer(this::onResultAngle))
        binding.calculateButton.setOnClickListener(this::onCalculate)



    }
    private fun onRestore(state:BackTaperModel):Unit{
        binding.inputDiameter.text = strToEdit(text = state.toolOd)
        binding.lowering.text = strToEdit(text = state.lowering)
        binding.length.text = strToEdit(text = state.length)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.setState(tool = binding.inputDiameter.text.toString(), lowOd = binding.lowering.text.toString(), len = binding.length.text.toString())
        super.onSaveInstanceState(outState)
    }
    private fun onResultOd(res:Double):Unit{
        binding.resultOd.text = strToEdit(text = res.toString())
    }
    private fun onResultAngle(res:Double):Unit{
        val response = "Full back taper is $res degrees, ${(res/2).toString()} degrees on side"
        binding.resultAngel.text = strToEdit(text = response)
    }
    private fun onCalculate(view:View):Unit{
        val od = binding.inputDiameter.text.toString().ifBlank { "0" }.toDouble()
        val lowering = binding.lowering.text.toString().ifBlank { "0" }.toDouble()
        val length = binding.length.text.toString().ifBlank { "0" }.toDouble()
        viewModel.calculate(toolOd = od, lowering=lowering, length = length)
        hideKeyboard()
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    private fun strToEdit(text:String):Editable = Editable.Factory().newEditable(text)
    private fun hideKeyboard():Unit{
        val inputService = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputService.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}