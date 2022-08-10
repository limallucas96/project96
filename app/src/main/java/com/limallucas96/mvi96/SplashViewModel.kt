package com.limallucas96.mvi96

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limallucas96.core_data.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val catRepository: CatRepository) : ViewModel() {

    fun fetchCats() {
        viewModelScope.launch {
            catRepository.getCats()
        }
    }

}