package com.example.medicalApp.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.medicalApp.db.AppDatabase
import com.example.medicalApp.db.PatientCard
import com.example.medicalApp.db.PatientCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Класс вьюмодели, принимает методы из репозитория
class PatientCardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PatientCardRepository

    init {
        val patientDao = AppDatabase.getDatabase(application).patientDao()
        repository = PatientCardRepository(patientDao)
    }
    fun getData(): List<PatientCard> {
        return repository.getData()
    }

    fun addPatientCard(patientCard: PatientCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(patientCard)
        }
    }

    fun updatePatientCard(patientCard: PatientCard) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePatient(patientCard)
        }
    }

    fun deletePatientCardById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePatientById(id)
        }
    }
}
