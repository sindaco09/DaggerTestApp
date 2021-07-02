package com.indaco.daggertestapp.data.storage.cache

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import com.indaco.daggertestapp.data.storage.dao.UserDao
import com.indaco.daggertestapp.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCache @Inject constructor(
    private val userDao: UserDao,
    private val sp: SharedPreferences
) {

    companion object {
        private const val KEY_CURRENT_USER = "current_user"
    }

    @WorkerThread
    fun addUser(user: User) = userDao.addUser(user)

    @WorkerThread
    fun removeUser(user: User) = userDao.removeUser(user)

    @WorkerThread
    fun getUser(email: String): User? = userDao.getUser(email)

    @WorkerThread
    fun getUsers(): List<User?>? = userDao.getUsers()

    var currentUser: User? = null
        get() = sp.getString(KEY_CURRENT_USER, null)?.let { User(it) }
        set(value) {
            field = value.also { sp.edit().putString(KEY_CURRENT_USER, it?.email).apply() }
        }
}