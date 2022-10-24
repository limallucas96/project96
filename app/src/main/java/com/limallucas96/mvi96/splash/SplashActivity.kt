package com.limallucas96.mvi96.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIActivity
import com.limallucas96.mvi96.databinding.ActivitySplashBinding
import com.limallucas96.navigator.featurehome.FeatureHomeNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity :
    BaseMVIActivity<ActivitySplashBinding, SplashAction, SplashViewState, SplashSideEffect>() {

    @set:Inject
    lateinit var featureHomeNavigator: FeatureHomeNavigator

    override val viewModel: SplashViewModel by viewModels()

    override fun inflateBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(SplashAction.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: SplashViewState) {
        // nothing to do here
    }

    override fun onSideEffectReceived(sideEffect: SplashSideEffect) {
        when (sideEffect) {
            SplashSideEffect.NavigateToHome -> {
                startActivity(featureHomeNavigator.newIntent(this))
                finish()
            }
        }
    }

}