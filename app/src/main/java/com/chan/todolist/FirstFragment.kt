package com.chan.todolist

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chan.domain.model.DomainTodo
import com.chan.todolist.adapter.TodoAdapter
import com.chan.todolist.databinding.FragmentFirstBinding
import com.chan.todolist.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var adapter: TodoAdapter

    private var _binding: FragmentFirstBinding? = null
    private val mainModel: TodoViewModel by activityViewModels()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainModel.getTodo()

        binding.tvDate.text = mainModel.curDate
        binding.tvWeek.text = mainModel.curDayOfWeek
        if (mainModel.curDayOfWeek == "일요일") {
            binding.tvWeek.setTextColor((Color.parseColor("#ED2939")))
        }

        binding.rvTodo.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



        adapter = TodoAdapter().apply {
            setHasStableIds(true)
        }
        adapter.onItemDel = {
            mainModel.delTodo(it)
        }

        adapter.onItemCheck = {
            mainModel.updateTodo(it)
        }


        adapter.onItemClick = {
            mainModel.updateCurTodo(it)
            findNavController().navigate(R.id.action_FirstFragment_to_writeFragment)
        }

        adapter.onItemMove = {from,to->


        }

        binding.rvTodo.adapter = adapter


        lifecycleScope.launch {
            mainModel.itemState.collectLatest {
                if (it != null) {
                    binding.tvTodoCnt.text = it.size.toString()
                    adapter.setData(it)
                }
            }
        }
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_writeFragment)
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}