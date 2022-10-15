package com.example.medicalApp.db

class PatientCardRepository(private val patientCardDao: PatientCardDao) {

    fun getData(): List<PatientCard> {
        return patientCardDao.getData()
    }

    fun addPatient(patientCard: PatientCard) {
        patientCardDao.addPatientCard(patientCard)
    }

    fun updatePatient(patientCard: PatientCard) {
        patientCardDao.updatePatientCard(patientCard)
    }

    fun deletePatientById(id: Int) {
        patientCardDao.deletePatientCardByID(id)
    }
}

