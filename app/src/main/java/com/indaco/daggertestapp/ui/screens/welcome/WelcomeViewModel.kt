package com.indaco.daggertestapp.ui.screens.welcome

import androidx.lifecycle.ViewModel
import com.indaco.daggertestapp.data.repositories.UserRepository
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    // set current user to null
    fun logout() = userRepository.logout()
}