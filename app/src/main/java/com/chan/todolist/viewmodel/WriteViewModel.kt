package com.chan.todolist.viewmodel

import androidx.lifecycle.ViewModel
import com.chan.todolist.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class WriteViewModel @Inject constructor(
    dateUtil: DateUtil,
) : ViewModel() {
    private var _dateState: MutableStateFlow<String> =
        MutableStateFlow(dateUtil.getDate())
    val dateState: StateFlow<String> = _dateState


    fun updateDate(year: Int, month: Int, dayOfMonth: Int) {
        _dateState.value = "$year.$month.$dayOfMonth"
    }
}