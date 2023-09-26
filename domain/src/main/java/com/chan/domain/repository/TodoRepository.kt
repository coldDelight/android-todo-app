package com.chan.domain.repository

import com.chan.domain.model.DomainTodo

interface TodoRepository {
    suspend fun getTodo(date:String): List<DomainTodo>

    suspend fun postTodo(todo: DomainTodo)

    suspend fun delTodo(id: Int)

    suspend fun updateTodo(id: Int)
}