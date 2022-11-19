package com.limallucas96.feature_one.entrypoint

import com.example.analytics.analytics.Analytics
import com.example.analytics.analytics.Events
import com.example.analytics.analytics.Events.FEATURE_HOME_ENTRY_POINT_CREATION_EVENT
import com.example.analytics.analytics.Events.FEATURE_ONE_ENTRY_POINT_CREATION_EVENT
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeatureOneEntryPointViewModel @Inject constructor(
    private val analytics: Analytics
) :
    BaseMVIViewModel<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect>() {

    override fun createInitialViewState() = FeatureOneEntryPointViewState()

    override fun handleUserAction(action: FeatureOneEntryPointAction, currentState: FeatureOneEntryPointViewState) {
        when (action) {
            FeatureOneEntryPointAction.OnCreate -> {
                analytics.logFirebaseEvent(FEATURE_ONE_ENTRY_POINT_CREATION_EVENT)
                emitSideEffect(
                    FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment(
                        FEATURE_ONE_ACTIVITY_BACK_STACK
                    )
                )
            }
        }
    }

    companion object {
        private const val FEATURE_ONE_ACTIVITY_BACK_STACK = "FEATURE_ONE_ACTIVITY_BACK_STACK"
    }
}