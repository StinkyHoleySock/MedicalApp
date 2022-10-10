package com.example.medicalApp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_card")
data class PatientCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String,
    val surname:String,
    val lastname:String,
    val date:String,
    val dischargeDate: String,
    val diagnosis: String,
    val doctor: String,
    val cardLink: String
)
