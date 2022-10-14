package com.example.medicalApp.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.medicalApp.R
import com.example.medicalApp.databinding.FragmentDetailsBinding
import com.example.medicalApp.db.PatientCard
import com.example.medicalApp.vm.PatientCardViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: PatientCardViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
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

        val builder = AlertDialog.Builder(context)
        builder
            .setMessage("Вы действительно хотите удалить карточку пациента?")
            .setCancelable(true)
            .setPositiveButton("Удалить") { _, _ ->
                deleteData()
                findNavController().navigate(R.id.action_detailsFragment_to_listFragment)
                Toast.makeText(context, getString(R.string.data_is_deleted), Toast.LENGTH_SHORT)
                    .show()

            }
            .setNegativeButton("Отмена") { _, _ -> }

        binding.buttonSubmit.setOnClickListener {
            updateData()
            findNavController().navigate(R.id.action_detailsFragment_to_listFragment)
        }

        binding.buttonDelete.setOnClickListener {
            builder.create().show()
        }
    }

    private fun updateData() {
        val name = binding.etName.text.toString()
        val surname = binding.etSurname.text.toString()
        val lastname = binding.etLastname.text.toString()
        val date = binding.etDate.text.toString()
        val dischargeDate = binding.etDischargeDate.text.toString()
        val diagnosis = binding.etDiagnosis.text.toString()
        val doctor = binding.etDoctor.text.toString()
        val cardLink = binding.etCard.text.toString()

        viewModel.updatePatientCard(
            PatientCard(
                args.patientCard.id, name, surname, lastname, date, dischargeDate, diagnosis,
                doctor, cardLink
            )
        )
    }

    private fun deleteData() {
        viewModel.deletePatientCardById(args.patientCard.id)
    }
}