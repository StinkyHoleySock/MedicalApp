package com.example.medicalApp.ui

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalApp.R
import com.example.medicalApp.db.PatientCard

class ListAdapter : RecyclerView.Adapter<ListAdapter.PatientCardViewHolder>() {

    private var patientList = emptyList<PatientCard>()
    inner class PatientCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.tv_fullName)
        val diagnosis: TextView = itemView.findViewById(R.id.tv_diagnosis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientCardViewHolder {
        return PatientCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_patient, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PatientCardViewHolder, position: Int) {

        val currentItem = patientList[position]
        val fullName = "${currentItem.surname} ${currentItem.name} ${currentItem.lastname}"
        holder.fullName.text = fullName
        holder.diagnosis.text = currentItem.diagnosis

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = patientList.size

    fun setData(patients: List<PatientCard>) {
        this.patientList = patients
        notifyDataSetChanged()
    }
}