package com.example.abtest

import javax.inject.Inject

class AbtestImpl @Inject constructor(): Abtest {

    override fun showLastPet(): Boolean {
        // Simulates ab test from firebase or whatever
        return true
    }

}