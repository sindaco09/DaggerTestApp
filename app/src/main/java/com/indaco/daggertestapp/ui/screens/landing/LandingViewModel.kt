package com.indaco.daggertestapp.ui.screens.landing

import androidx.lifecycle.ViewModel
import com.indaco.daggertestapp.data.repositories.UserRepository
import com.indaco.daggertestapp.data.model.User
import javax.inject.Inject

class LandingViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    // if user exists, user is logged in
    fun isLoggedIn(): Pair<Boolean, User?> {
        return repository.loggedInUser.let { Pair(it != null, it) }
    }
}