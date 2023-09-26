package com.chan.todolist.di

import android.content.Context
import androidx.room.Room
import com.chan.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TodoDatabase {
        return Room.databaseBuilder(
            appContext, TodoDatabase::class.java, "todo_db"
        ).fallbackToDestructiveMigration().build()
    }


}

