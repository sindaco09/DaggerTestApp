package com.indaco.daggertestapp.data.repositories

import com.indaco.daggertestapp.data.model.AuthForm
import com.indaco.daggertestapp.data.storage.cache.UserCache
import com.indaco.daggertestapp.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userCache: UserCache) {

    val loggedInUser: User? get() = userCache.currentUser

    fun login(authForm: AuthForm): Flow<User?> {
        return flow {
            val user = userCache.getUser(authForm.email)
            userCache.currentUser = user.also { emit(it) }
        }
    }

    fun register(user: User): Flow<User?> {
        return flow {
            // API call
            userCache.addUser(user)
            userCache.currentUser = user.also { emit(it) }
        }
    }

    fun logout() {
        userCache.currentUser = null
    }

}