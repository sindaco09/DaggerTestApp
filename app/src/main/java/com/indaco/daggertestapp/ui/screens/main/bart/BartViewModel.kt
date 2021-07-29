package com.indaco.daggertestapp.ui.screens.main.bart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indaco.daggertestapp.core.espresso.TestCountingIdlingResource
import com.indaco.daggertestapp.data.model.bart.BartStation
import com.indaco.daggertestapp.data.repositories.BartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BartViewModel @Inject constructor(
    private val repository: BartRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _allStations = MutableLiveData<List<BartStation>>()
    val allStations: LiveData<List<BartStation>> get() = _allStations

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getStations() =
        viewModelScope.launch(dispatcher) {
            repository.getBartStations().collect {
                if (it.isSuccessful)
                    _allStations.postValue(it.body())
                else
                    _errorLiveData.postValue(it.errorBody().toString())
            }
        }
}