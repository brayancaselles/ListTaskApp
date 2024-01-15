package com.brayandev.listtaskapp.di

import android.content.Context
import androidx.room.Room
import com.brayandev.listtaskapp.data.dataBase.TaskDataBase
import com.brayandev.listtaskapp.data.dataBase.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): TaskDataBase {
        return Room.databaseBuilder(context, TaskDataBase::class.java, "AllDataBase").build()
    }


    @Provides
    @Singleton
    fun provideTaskDao(db: TaskDataBase): TaskDao {
        return db.taskDao()
    }
}
