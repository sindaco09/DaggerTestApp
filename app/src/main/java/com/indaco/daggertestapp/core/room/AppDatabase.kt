package com.indaco.daggertestapp.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indaco.daggertestapp.data.testmodules.storage.dao.UserDao
import com.indaco.daggertestapp.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
