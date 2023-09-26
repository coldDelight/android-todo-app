package com.chan.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chan.domain.model.DomainTodo
import com.chan.domain.usecase.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCase: TodoUseCase
) : ViewModel() {
    private var _itemList: MutableLiveData<List<DomainTodo>> = MutableLiveData(listOf())
    val itemList: MutableLiveData<List<DomainTodo>>
        get() = _itemList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val tmpDate = "20230925"
            _itemList.postValue(useCase.invoke(tmpDate))
        }
    }
}