package com.example.medicalApp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.medicalApp.R
import com.example.medicalApp.databinding.FragmentEditBinding
import com.example.medicalApp.db.PatientCard
import com.example.medicalApp.vm.PatientCardViewModel

class EditFragment : Fragment(R.layout.fragment_edit) {
    //Инициализация биндинга
    private lateinit var binding: FragmentEditBinding
    //Инициализация класса viewModel с помощью делегата
    private val viewModel: PatientCardViewModel by viewModels()
    //Инициализация аргументов (navigation component)
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            etName.setText(args.patientCard.name)
            etSurname.setText(args.patientCard.surname)
            etLastname.setText(args.patientCard.lastname)
            etDate.setText(args.patientCard.date)
            etDischargeDate.setText(args.patientCard.dischargeDate)
            etDiagnosis.setText(args.patientCard.diagnosis)
            etDoctor.setText(args.patientCard.doctor)
            etCard.setText(args.patientCard.cardLink)
        }

        //Логика отображения диалогового окна
        val builder = AlertDialog.Builder(context)
        builder
            .setMessage("Вы действительно хотите удалить карточку пациента?")
            .setCancelable(true)
            .setPositiveButton("Удалить") { _, _ ->
                deleteData()
                findNavController().navigate(R.id.edit_to_list)
                Toast.makeText(context, getString(R.string.data_is_deleted), Toast.LENGTH_SHORT)
                    .show()

            }
            .setNegativeButton("Отмена") { _, _ -> }

        //Использование биндинга и назначение слушателя кнопке
        binding.buttonSubmit.setOnClickListener {
            updateData()
        }

        binding.buttonDelete.setOnClickListener {
            builder.create().show()
        }
    }
    //Метод для обновления карточки пациента в базу данных
    private fun updateData() {
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
                PatientCard(args.patientCard.id, name, surname, lastname, date, dischargeDate, diagnosis, doctor, cardLink)
            viewModel.updatePatientCard(patientCard)
            findNavController().navigate(R.id.edit_to_list)
        } else Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_SHORT).show()
    }
    //Метод для удаления карточки пациента из базы
    private fun deleteData() {
        viewModel.deletePatientCardById(args.patientCard.id)
    }
    //Метод для проверки вводимых данных на пустую строку
    private fun inputCheck(
        name: String, surname: String, date: String, diagnosis: String, doctor: String
    ): Boolean {
        return ((name.isNotEmpty()) && (surname.isNotEmpty()) && (date.isNotEmpty())
                && (diagnosis.isNotEmpty()) && (doctor.isNotEmpty()))
    }
}