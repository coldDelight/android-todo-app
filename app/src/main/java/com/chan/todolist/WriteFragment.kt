package com.chan.todolist

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chan.todolist.databinding.FragmentWriteBinding
import com.chan.todolist.viewmodel.TodoViewModel
import com.chan.todolist.viewmodel.WriteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class WriteFragment : BottomSheetDialogFragment() {


    private var _binding: FragmentWriteBinding? = null
    private val viewModel: WriteViewModel by viewModels()
    private val mainModel: TodoViewModel by activityViewModels()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mainModel.isUpdate) {
            binding.etTodo.setText(mainModel.curTodoState.value.name)
        }

        binding.btnTodoSave.setOnClickListener {
            if (mainModel.isUpdate) {
                mainModel.updateCurTodo(binding.etTodo.text.toString(), viewModel.dateState.value)
                mainModel.initCurTodo()
            } else {
                mainModel.postTodo(binding.etTodo.text.toString(), viewModel.dateState.value)
            }
            findNavController().popBackStack()
        }
        binding.btnDate.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(requireContext())
            datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.updateDate(year, month, dayOfMonth)
            }
            datePickerDialog.show()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}