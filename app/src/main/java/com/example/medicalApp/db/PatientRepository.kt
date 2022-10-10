package com.example.medicalApp.db

import androidx.lifecycle.MutableLiveData

class PatientRepository(private val patientCardDao: PatientCardDao) {

    val readData: MutableLiveData<List<PatientCard>> = patientCardDao.getData()

    fun addGame(patientCard: PatientCard) {
        patientCardDao.addPatientCard(patientCard)
    }

    fun updateGame(patientCard: PatientCard) {
        patientCardDao.updatePatientCard(patientCard)
    }

    fun deleteGame(patientCard: PatientCard) {
        patientCardDao.deletePatientCard(patientCard)
    }
}   