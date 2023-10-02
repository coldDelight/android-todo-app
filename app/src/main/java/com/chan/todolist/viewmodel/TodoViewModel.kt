package com.chan.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chan.domain.model.DomainTodo
import com.chan.domain.usecase.TodoUseCase
import com.chan.todolist.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private var _curTodoState: MutableStateFlow<DomainTodo> =
        MutableStateFlow(DomainTodo("", curDate, false, -1))
    val curTodoState: StateFlow<DomainTodo> = _curTodoState

    var isUpdate = false


    fun getTodo() {
        viewModelScope.launch() {
            val data = useCase.invoke(curDate)
            Log.e("TAG", "getTodo: ${data.sortedBy { it.isDone }}", )

            _itemState.value = useCase.invoke(curDate).sortedWith(compareBy<DomainTodo> { !it.isDone }.thenBy { it.order })

        }
    }

    fun postTodo(name: String, date: String) {
        viewModelScope.launch() {
            val tmpData = useCase.invoke(dateUtil.getDate())
            var curIndex = 1
            if (tmpData.isNotEmpty()) {
                curIndex = tmpData.maxBy { it.order }.order + 1
            }
            val data = DomainTodo(name, date, false, curIndex)
//            Log.e("TAG", "postTodo: ${data}")
            useCase.postTodo(data)
            getTodo()
        }
    }

    fun delTodo(id: Int) {
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                useCase.delTodo(id)
                getTodo()
            }
        }
    }

    fun updateTodo(todo: DomainTodo) {
        val data = DomainTodo(todo.name, todo.date, !todo.isDone, todo.order, todo.id)
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                useCase.updateTodo(data)
                getTodo()
            }
        }
    }


    fun updateCurTodo(name: String, date: String) {
        val data = DomainTodo(
            name,
            date,
            curTodoState.value.isDone,
            curTodoState.value.order,
            curTodoState.value.id
        )
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                useCase.updateTodo(data)
                getTodo()
            }
        }
        _curTodoState.value = data
        isUpdate = false
    }

    fun updateCurTodo(todo: DomainTodo) {
        isUpdate = true
        _curTodoState.value = todo
    }

    fun initCurTodo() {
        _curTodoState.value = DomainTodo("", curDate, false, -1)
    }
}