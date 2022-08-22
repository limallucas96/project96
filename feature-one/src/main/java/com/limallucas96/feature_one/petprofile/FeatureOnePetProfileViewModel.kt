package com.limallucas96.feature_one.petprofile

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureOnePetProfileViewModel : BaseMVIViewModel<FeatureOnePetProfileEvents, FeatureOnePetProfileViewState, FeatureOnePetProfileSideEffects>() {

    override fun createInitialViewState() = FeatureOnePetProfileViewState()

    override fun handleEvent(event: FeatureOnePetProfileEvents) {
        when (event) {
            is FeatureOnePetProfileEvents.ViewScreen -> {
                setSideEffect(FeatureOnePetProfileSideEffects.ShowToast)
                setState { copy(screenText = "Welcome") }
            }
        }
    }

}