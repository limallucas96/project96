package com.limallucas96.feature_one

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FeatureOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_one)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FeatureOneActivity::class.java)
        }
    }
}