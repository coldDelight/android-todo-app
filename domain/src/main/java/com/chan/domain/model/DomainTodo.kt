package com.chan.domain.model

data class DomainTodo(
    val name: String,
    val date: String,
    val isDone: Boolean,
    val order: Int,
    val id: Int=-1,
)
