package com.example.medicalApp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalApp.R
import com.example.medicalApp.db.PatientCard

class ListAdapter : RecyclerView.Adapter<ListAdapter.GameViewHolder>() {

    private var patientList = emptyList<PatientCard>()

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_patient, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = patientList[position]
//        holder.itemView. = currentItem.title

        holder.itemView.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = patientList.size

    fun setData(game: List<PatientCard>) {
        this.patientList = game
        notifyDataSetChanged()
    }
}