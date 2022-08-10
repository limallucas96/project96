package com.limallucas96.mvi96

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.limallucas96.feature_one.FeatureOneActivity
import com.limallucas96.feature_two.FeatureTwoActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(FeatureOneActivity.getIntent(this))
    }

}