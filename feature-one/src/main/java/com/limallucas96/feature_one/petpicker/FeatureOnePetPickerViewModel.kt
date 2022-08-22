package com.limallucas96.feature_one.petpicker

import com.limallucas96.core_presentation.mvi.BaseMVIViewModel

class FeatureOnePetPickerViewModel :
    BaseMVIViewModel<FeatureOnePetPickerEvents, FeatureOnePetPickerViewState, FeatureOnePetPickerSideEffects>() {

    override fun createInitialViewState() = FeatureOnePetPickerViewState()

    override fun handleEvent(event: FeatureOnePetPickerEvents) {

    }

}