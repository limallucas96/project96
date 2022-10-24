package com.limallucas96.feature_home.entrypoint

import android.os.Bundle
import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIActivity
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.feature_home.databinding.ActivityFeatureHomeEntryPointBinding
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeatureHomeEntryPointActivity : BaseMVIActivity<ActivityFeatureHomeEntryPointBinding, HomeAction, HomeViewState, HomeSideEffect>() {

    @set:Inject
    lateinit var featureOneNavigator: FeatureOneNavigator

    @set:Inject
    lateinit var featureTwoNavigator: FeatureTwoNavigator

    override fun inflateBinding() = ActivityFeatureHomeEntryPointBinding.inflate(layoutInflater)

    override val viewModel : FeatureHomeEntryPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        viewModel.dispatch(HomeAction.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: HomeViewState) {
        binding.textViewPetCounter.text = viewState.petCounter
    }

    override fun onSideEffectReceived(sideEffect: HomeSideEffect) {
        when (sideEffect) {
            HomeSideEffect.NavigateToFeatureOne -> {
                startActivity(featureOneNavigator.newIntent(this))
            }
            HomeSideEffect.NavigateToFeatureTwo -> {
                startActivity(featureTwoNavigator.newIntent(this))
            }
        }
    }

    private fun setupListeners() {
        binding.run {
            buttonFeatureOne.setOnClickListener {
                viewModel.dispatch(HomeAction.PrimaryButtonClick)
            }
            buttonFeatureTwo.setOnClickListener {
                viewModel.dispatch(HomeAction.SecondaryButtonClick)
            }
        }
    }

}