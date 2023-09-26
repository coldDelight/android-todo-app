package com.chan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chan.data.local.dao.TodoDao
import com.chan.data.local.entity.TodoEntity

@Database(

    entities = [TodoEntity::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}