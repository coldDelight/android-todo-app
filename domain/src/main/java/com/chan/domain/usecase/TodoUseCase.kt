package com.chan.domain.usecase
import com.chan.domain.model.DomainTodo
import com.chan.domain.repository.TodoRepository

class TodoUseCase(private val repository: TodoRepository) {

    suspend operator fun invoke(date:String): List<DomainTodo> {
        return repository.getTodo(date)
    }

    suspend fun updateTodo(id: Int) {
        return repository.updateTodo(id)
    }

    suspend fun delTodo(id: Int) {
        return repository.delTodo(id)
    }

    suspend fun postTodo(todo: DomainTodo) {
        return repository.postTodo(todo)
    }

}


