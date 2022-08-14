package com.limallucas96.mvi96.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val catRepository: CatRepository,
    private val sharedPreferencesRepository: AppSharedPreferences
) : ViewModel() {

    fun fetchCats() {
        viewModelScope.launch {
            val cats = catRepository.getCats()
            sharedPreferencesRepository.putString("TEMP", "TEST")
            val a = sharedPreferencesRepository.getString("TEMP")
            println()
        }
    }

}