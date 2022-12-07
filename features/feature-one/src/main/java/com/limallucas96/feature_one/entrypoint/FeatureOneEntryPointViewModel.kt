package com.limallucas96.feature_one.entrypoint

import com.example.analytics.analytics.Analytics
import com.example.analytics.analytics.Events.FEATURE_ONE_ENTRY_POINT_CREATION_EVENT
import com.limallucas96.core_common.AppDispatcherProvider
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.feature_one.enums.CatProfileProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeatureOneEntryPointViewModel @Inject constructor(
    private val analytics: Analytics,
    private val resourcesProvider: ResourcesProvider,
    private val dispatcher: AppDispatcherProvider
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
            is FeatureOneEntryPointAction.UpdateToolbar -> {
                updateViewState {
                    copy(
                        isProgressBarVisible = action.catProfileProgress != CatProfileProgress.NONE,
                        progressBarStep = action.catProfileProgress.step,
                        progressBarMax = CatProfileProgress.getSumOfSteps(),
                        isToolbarVisible = action.catProfileProgress != CatProfileProgress.NONE,
                        toolbarTitle = resourcesProvider.getString(action.catProfileProgress.stringRes)
                    )
                }
            }
        }
    }

    companion object {
        private const val FEATURE_ONE_ACTIVITY_BACK_STACK = "FEATURE_ONE_ACTIVITY_BACK_STACK"
    }
}