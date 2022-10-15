package com.example.medicalApp.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "patient_card")
data class PatientCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val surname: String,
    val lastname: String,
    val date: String,
    val dischargeDate: String,
    val diagnosis: String,
    val doctor: String,
    val cardLink: String
) : Parcelable

