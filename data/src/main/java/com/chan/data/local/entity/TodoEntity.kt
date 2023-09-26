package com.chan.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chan.domain.model.DomainTodo

@Entity(tableName = "todo")
data class TodoEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "isDone") val isDone: Boolean,
    @ColumnInfo(name = "order") val order: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

) {
    fun toDomainTodo(): DomainTodo {
        return DomainTodo(
            id = id,
            name = name,
            date = date,
            isDone = isDone,
            order = order
        )
    }
}