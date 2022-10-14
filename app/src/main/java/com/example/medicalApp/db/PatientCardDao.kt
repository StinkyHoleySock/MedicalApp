package com.example.medicalApp.db

import androidx.room.*

@Dao
interface PatientCardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPatientCard(patientCard: PatientCard)

    @Update
    fun updatePatientCard(patientCard: PatientCard)

    @Query("DELETE FROM patient_card WHERE id = :id")
    fun deletePatientCardByID(id: Int)

    @Query("SELECT * FROM patient_card")
    fun getData(): List<PatientCard>
}
