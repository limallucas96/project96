package com.limallucas96.feature_one.petpicker

import android.view.LayoutInflater
import android.view.ViewGroup
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.feature_one.databinding.FragmentPetPickerBinding

class FeatureOnePetPickerFragment :
    BaseMVINavigationFragment<FragmentPetPickerBinding, FeatureOnePetPickerEvents, FeatureOnePetPickerViewState, FeatureOnePetPickerSideEffects>() {

//    override val viewModel: FeatureOneViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPetPickerBinding {
        return FragmentPetPickerBinding.inflate(inflater, container, false)
    }

    override fun onViewStateUpdated(viewState: FeatureOnePetPickerViewState) {

    }

    override fun onSideEffectReceived(sideEffect: SideEffect) {

    }

    override fun onViewReady() {

    }

    companion object {
        fun newInstance() = FeatureOnePetPickerFragment()
    }

}