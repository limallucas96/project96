package com.limallucas96.feature_one.featureone

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureOneViewModel : BaseMVIViewModel<FeatureOneEvents, FeatureOneViewState, FeatureOneSideEffects>() {

    override fun createInitialViewState() = FeatureOneViewState()

    override fun handleEvent(event: FeatureOneEvents) {
        when (event) {
            is FeatureOneEvents.ViewScreen -> {
                setSideEffect(FeatureOneSideEffects.ShowToast)
                setState { copy(screenText = "Welcome") }
            }
        }
    }

}