package com.example.medicalApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalApp.R
import com.example.medicalApp.databinding.FragmentAddBinding
import com.example.medicalApp.databinding.FragmentEditBinding
import com.example.medicalApp.db.PatientCard
import com.example.medicalApp.vm.PatientCardViewModel

class AddFragment : Fragment(R.layout.fragment_add) {
    //Инициализация биндинга
    private lateinit var binding: FragmentAddBinding
    //Инициализация класса viewModel с помощью делегата
    private val viewModel: PatientCardViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Использование биндинга и назначение слушателя кнопке
        binding.buttonSubmit.setOnClickListener {
            insertDataToDatabase()
        }
    }
    //Метод для вставки карточки пациента в базу данных
    private fun insertDataToDatabase() {
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()
        val lastname = binding.etLastname.text.toString()
        val date = binding.etDate.text.toString()
        val dischargeDate = binding.etDischargeDate.text.toString()
        val diagnosis = binding.etDiagnosis.text.toString()
        val doctor = binding.etDoctor.text.toString()
        val cardLink = binding.etCard.text.toString()

        if (inputCheck(name, surname, date, diagnosis, doctor)) {
            val patientCard =
                PatientCard(0, name, surname, lastname, date, dischargeDate, diagnosis, doctor, cardLink)
            viewModel.addPatientCard(patientCard)
            findNavController().navigate(R.id.add_to_list)
        } else Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_SHORT).show()
    }

    //Метод для проверки вводимых данных на пустую строку
    private fun inputCheck(
        name: String, surname: String, date: String, diagnosis: String, doctor: String
    ): Boolean {
        return ((name.isNotEmpty()) && (surname.isNotEmpty()) && (date.isNotEmpty())
                && (diagnosis.isNotEmpty()) && (doctor.isNotEmpty()))
    }
}