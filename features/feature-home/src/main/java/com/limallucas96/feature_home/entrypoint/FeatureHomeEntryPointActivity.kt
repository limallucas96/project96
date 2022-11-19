package com.limallucas96.feature_home.entrypoint

import android.os.Bundle
import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationActivity
import com.limallucas96.feature_home.home.HomeFragment
import com.limallucas96.navigator.featurehome.FeatureHomeNavigator
import com.limallucas96.navigator.fragment.FragmentNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeatureHomeEntryPointActivity :
    BaseMVINavigationActivity<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect>() {

    @set:Inject
    lateinit var navigator: FragmentNavigator

    override val viewModel: FeatureHomeEntryPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(FeatureHomeEntryPointAction.OnCreate)
    }

    override fun onViewStateUpdated(viewState: FeatureHomeEntryPointViewState) {
        // nothing to do here
        // TODO implement this in base so we dont need to implement her
    }

    override fun onSideEffectReceived(sideEffect: FeatureHomeEntryPointSideEffect) {
        when (sideEffect) {
            FeatureHomeEntryPointSideEffect.NavigateToHomeFragment -> {
                navigator.navigateTo(
                    this,
                    fragment = HomeFragment.newInstance()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}