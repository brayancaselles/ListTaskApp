package com.brayandev.listtaskapp.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brayandev.listtaskapp.data.dataBase.dao.TaskDao
import com.brayandev.listtaskapp.data.dataBase.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}