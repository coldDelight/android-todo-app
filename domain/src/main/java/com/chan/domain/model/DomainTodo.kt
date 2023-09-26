package com.chan.domain.model

data class DomainTodo(
    val id: Int,
    val name: String,
    val date: String,
    val isDone: Boolean,
    val order: String,
)
