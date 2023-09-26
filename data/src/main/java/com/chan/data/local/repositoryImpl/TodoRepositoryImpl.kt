package com.chan.data.local.repositoryImpl

import com.chan.data.local.dao.TodoDao
import com.chan.data.local.entity.TodoEntity
import com.chan.domain.model.DomainTodo
import com.chan.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getTodo(date: String): List<DomainTodo> {
        return dao.getTodo(date).map { it.toDomainTodo() }
    }

    override suspend fun postTodo(todo: DomainTodo) {
        dao.insertTodo(TodoEntity(todo.name, todo.date, todo.isDone, todo.order))
    }

    override suspend fun delTodo(id: Int) {
        dao.delTodo(id)
    }

    override suspend fun updateTodo(id: Int) {
        TODO("Not yet implemented")
    }
}