package com.example.medicalApp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicalApp.R
import com.example.medicalApp.databinding.FragmentListBinding
import com.example.medicalApp.vm.PatientCardViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListFragment : Fragment(R.layout.fragment_list) {
    //Инициализация биндинга
    private lateinit var binding: FragmentListBinding
    //Инициализация класса viewModel с помощью делегата
    private val viewModel: PatientCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListAdapter()
        val recyclerView = binding.rvList
        //Назначение адаптера и layoutManager'а для recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //Если список пустой, то отображается надпись
        CoroutineScope(Dispatchers.IO).launch {
            adapter.setData(viewModel.getData())
            if (adapter.itemCount > 0) {
                binding.tvAddData.visibility = View.GONE
            }
        }
        //Использование биндинга и назначение слушателя кнопке
        binding.fabAddPatient.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}