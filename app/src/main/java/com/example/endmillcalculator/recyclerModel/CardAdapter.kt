package com.example.endmillcalculator.recyclerModel

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.endmillcalculator.R
import com.example.endmillcalculator.databinding.CardItemBinding
import com.example.endmillcalculator.models.CardModel
import java.util.zip.Inflater

class CardAdapter():RecyclerView.Adapter<CardAdapter.CardHolder>() {

    private val testCard = listOf<CardModel>(
            CardModel(title = "Eccentric contact", image = R.drawable.eccentric2, routeId = R.id.action_rootFragment_to_eccentricFragment),
            CardModel(title = "Back taper", image = R.drawable.back_drill, routeId = R.id.action_rootFragment_to_backTaperFragment),
            CardModel(title = "Helix to lead", image = R.drawable.helix, routeId = R.id.action_rootFragment_to_helixFragment))

    class CardHolder(private val viewBinding: CardItemBinding):RecyclerView.ViewHolder(viewBinding.root){

         fun bind(model: CardModel){
             viewBinding.cardTitle.text = model.title
             viewBinding.actionImage.setImageResource(model.image)

             val view = viewBinding.root

             view.setOnClickListener {
                 view.findNavController().navigate(model.routeId)
             }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)
        return  CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
            holder.bind(testCard[position])
    }

    override fun getItemCount(): Int = testCard.size
}