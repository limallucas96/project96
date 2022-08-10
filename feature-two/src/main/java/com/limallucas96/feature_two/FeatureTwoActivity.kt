package com.limallucas96.feature_two

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FeatureTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_two)
    }

    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context, FeatureTwoActivity::class.java)
        }
    }

}