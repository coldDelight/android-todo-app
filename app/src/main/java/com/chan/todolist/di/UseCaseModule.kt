package com.chan.todolist.di

import com.chan.domain.repository.TodoRepository
import com.chan.domain.usecase.TodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideTodoUseCase(repository: TodoRepository): TodoUseCase {
        return TodoUseCase(repository)
    }
}