package com.chan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chan.data.local.entity.TodoEntity

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("SELECT * FROM todo WHERE date = (:date)")
    suspend fun getTodo(date: String): List<TodoEntity>

//    @Query("SELECT * FROM todo WHERE id = (:id)")
//    suspend fun checkTodo(id: Int): List<TodoEntity>

    @Query("DELETE FROM todo WHERE id=(:id)")
    fun delTodo(id: Int)
}