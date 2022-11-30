package com.limallucas96.feature_one.entrypoint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import com.limallucas96.feature_one.enums.CatProfileProgress
import com.limallucas96.feature_one.enums.CatProfileProgress.Companion.getSumOfSteps
import com.limallucas96.feature_one.catprofile.CatProfileFragment
import com.limallucas96.navigator.fragment.FragmentNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeatureOneEntryPointActivity :
    BaseMVINavigationActivity<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect>() {

    @set:Inject
    lateinit var navigator: FragmentNavigator

    override val viewModel: FeatureOneEntryPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(FeatureOneEntryPointAction.OnCreate)
    }

    override fun onViewStateUpdated(viewState: FeatureOneEntryPointViewState) {
        binding.progressBar.isVisible = viewState.isProgressBarVisible
        binding.progressBar.progress = viewState.progressBarStep
        binding.progressBar.max = viewState.progressBarMax
        binding.toolbarNavigation.isVisible = viewState.isToolbarVisible
        binding.toolbarNavigation.title = viewState.toolbarTitle
    }

    override fun onSideEffectReceived(sideEffect: FeatureOneEntryPointSideEffect) {
        when (sideEffect) {
            is FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment -> {
                navigator.navigateTo(this, CatProfileFragment.newInstance(), sideEffect.backStack)
            }
        }
    }

}