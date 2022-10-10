package com.example.medicalApp.db

import androidx.lifecycle.MutableLiveData
import androidx.room.*

interface PatientCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPatientCard(patientCard: PatientCard)

    @Update
    fun updatePatientCard(patientCard: PatientCard)

    @Delete
    fun deletePatientCard(patientCard: PatientCard)

    @Query("SELECT * FROM patient_card")
    fun getData(): MutableLiveData<List<PatientCard>>
}
