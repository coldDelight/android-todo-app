package com.chan.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chan.domain.model.DomainTodo
import com.chan.domain.usecase.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCase: TodoUseCase
) : ViewModel() {
    private var _itemState: MutableStateFlow<List<DomainTodo>?> =
        MutableStateFlow(null)
    val itemState: StateFlow<List<DomainTodo>?> = _itemState

    fun getTodo(date:String) {
        viewModelScope.launch() {
//            useCase.postTodo(DomainTodo("2개정도 있다","20230926",false,2))

            _itemState.value = useCase.invoke(date)
        }
    }
}