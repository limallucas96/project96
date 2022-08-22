package com.limallucas96.feature_one.petprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.feature_one.databinding.FragmentPetProfileBinding
import com.limallucas96.feature_one.petpicker.FeatureOnePetPickerFragment

class FeatureOnePetProfileFragment :
    BaseMVINavigationFragment<FragmentPetProfileBinding, FeatureOnePetProfileEvents, FeatureOnePetProfileViewState, FeatureOnePetProfileSideEffects>() {

//    override val viewModel: FeatureOneViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPetProfileBinding {
        return FragmentPetProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewStateUpdated(viewState: FeatureOnePetProfileViewState) {
//        binding.textViewFeatureOne.text = viewState.screenText
    }

    override fun onSideEffectReceived(sideEffect: SideEffect) {
        when (sideEffect) {
            is FeatureOnePetProfileSideEffects.ShowToast -> showToast()
        }
    }

    override fun onViewReady() {
        showToast()
        binding.buttonContinue.setOnClickListener {
            navigateToPetPickerFragment()
        }
    }

    private fun showToast() {
        activity?.let {
            Toast.makeText(it, "Feature one toast", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToPetPickerFragment() {
        navigateTo(
            FeatureOnePetPickerFragment.newInstance(),
            FEATURE_ONE_PET_PROFILE_BACK_STACK
        )
    }

    companion object {
        private const val FEATURE_ONE_PET_PROFILE_BACK_STACK = "FEATURE_ONE_PET_PROFILE_BACK_STACK"
        fun newInstance() = FeatureOnePetProfileFragment()
    }

}