package com.example.jettodo

import android.content.Context
import androidx.room.Room
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "task_database").build()

    @Provides
    fun provideWeekDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabaseWeek::class.java, "week_task_database").build()

    @Provides
    fun provideMonthDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabaseMonth::class.java, "month_task_database").build()

    @Provides
    fun provideYearDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabaseYear::class.java, "year_task_database").build()

    @Provides
    fun provideDao(db: AppDatabase) = db.taskDao()

    @Provides
    fun provideWeekDao(db: AppDatabaseWeek) = db.weekTaskDao()

    @Provides
    fun provideMonthDao(db: AppDatabaseMonth) = db.monthTaskDao()

    @Provides
    fun provideYearDao(db: AppDatabaseYear) = db.yearTaskDao()
}
