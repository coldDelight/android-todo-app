package com.chan.todolist.di

import com.chan.data.local.TodoDatabase
import com.chan.data.local.repositoryImpl.TodoRepositoryImpl
import com.chan.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTodoRepository(
        db: TodoDatabase,
    ): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

}