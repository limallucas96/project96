package com.limallucas96.mvi96.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.limallucas96.mvi96.R
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    @set:Inject
    lateinit var featureOneNavigator: FeatureOneNavigator

    @set:Inject
    lateinit var featureTwoNavigator: FeatureTwoNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.fetchCats()

        findViewById<AppCompatButton>(R.id.button_featureOne).setOnClickListener {
            startActivity(featureOneNavigator.newIntent(this))
        }

        findViewById<AppCompatButton>(R.id.button_featureTwo).setOnClickListener {
            startActivity(featureTwoNavigator.newIntent(this))
        }
    }

}