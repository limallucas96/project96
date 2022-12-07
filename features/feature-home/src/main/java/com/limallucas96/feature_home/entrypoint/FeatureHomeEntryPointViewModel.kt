package com.limallucas96.feature_home.entrypoint

import com.example.analytics.analytics.Analytics
import com.example.analytics.analytics.Events.FEATURE_HOME_ENTRY_POINT_CREATION_EVENT
import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeatureHomeEntryPointViewModel @Inject constructor(
    private val analytics: Analytics,
    private val dispatcher: AppDispatcherProvider
) :
    BaseMVIViewModel<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect>() {

    override fun createInitialViewState() = FeatureHomeEntryPointViewState()

    override fun handleUserAction(action: FeatureHomeEntryPointAction, currentState: FeatureHomeEntryPointViewState) {
        when (action) {
            FeatureHomeEntryPointAction.OnCreate -> {
                analytics.logFirebaseEvent(FEATURE_HOME_ENTRY_POINT_CREATION_EVENT)
                emitSideEffect(FeatureHomeEntryPointSideEffect.NavigateToHomeFragment)
            }
        }
    }

}