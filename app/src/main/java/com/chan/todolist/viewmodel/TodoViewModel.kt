package com.chan.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chan.domain.model.DomainTodo
import com.chan.domain.usecase.TodoUseCase
import com.chan.todolist.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val useCase: TodoUseCase,
    private val dateUtil: DateUtil,

) : ViewModel() {
    private var _itemState: MutableStateFlow<List<DomainTodo>?> =
        MutableStateFlow(null)
    val itemState: StateFlow<List<DomainTodo>?> = _itemState
    val curDate = dateUtil.getDate()
    val curDayOfWeek = dateUtil.getDayWeek().plus("요일")

    fun getTodo() {
        viewModelScope.launch() {
            _itemState.value = useCase.invoke(curDate)

        }
    }
}