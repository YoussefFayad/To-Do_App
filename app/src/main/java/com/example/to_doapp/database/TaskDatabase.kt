package com.example.to_doapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Task::class], version = 1)
@TypeConverters(value = [DateTypeConverter::class])
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null
        val DATA_BASE_NAME = "Tasks Database"
        fun getInstance(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, TaskDatabase::class.java, DATA_BASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

            }
            return INSTANCE!!
        }
    }

}