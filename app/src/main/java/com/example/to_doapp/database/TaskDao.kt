package com.example.to_doapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date

@Dao
interface TaskDao {
    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM Todos")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM Todos WHERE date = :dateTime")
    fun getTasksByDate(dateTime: Date): List<Task>
}
