package com.limallucas96.mvi96.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIActivity
import com.limallucas96.mvi96.databinding.ActivitySplashBinding
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity :
    BaseMVIActivity<ActivitySplashBinding, SplashAction, SplashViewState, SplashSideEffect>() {

    @set:Inject
    lateinit var featureOneNavigator: FeatureOneNavigator

    @set:Inject
    lateinit var featureTwoNavigator: FeatureTwoNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        viewModel.dispatch(SplashAction.ViewScreen)
    }

    override fun inflateBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override val viewModel: SplashViewModel by viewModels()

    override fun onViewStateUpdated(viewState: SplashViewState) {
        binding.textViewPetCounter.text = viewState.petCounter
    }

    override fun onSideEffectReceived(sideEffect: SplashSideEffect) {
        when (sideEffect) {
            SplashSideEffect.NavigateToFeatureOne -> {
                startActivity(featureOneNavigator.newIntent(this))
            }
            SplashSideEffect.NavigateToFeatureTwo -> {
                startActivity(featureTwoNavigator.newIntent(this))
            }
        }
    }

    private fun setupListeners() {
        binding.run {
            buttonFeatureOne.setOnClickListener {
                viewModel.dispatch(SplashAction.PrimaryButtonClick)
            }
            buttonFeatureTwo.setOnClickListener {
                viewModel.dispatch(SplashAction.SecondaryButtonClick)
            }
        }
    }

}