package com.limallucas96.feature_one.catprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.feature_one.catpicker.CatPickerFragment
import com.limallucas96.feature_one.databinding.FragmentCatProfileBinding


class CatProfileFragment :
    BaseMVINavigationFragment<FragmentCatProfileBinding, CatProfileEvents, CatProfileViewState, CatProfileSideEffects>() {

    override val viewModel: CatProfileViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatProfileBinding {
        return FragmentCatProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewStateUpdated(viewState: CatProfileViewState) {
        binding.buttonContinue.isEnabled = viewState.isContinueButtonEnable
    }

    override fun onSideEffectReceived(sideEffect: CatProfileSideEffects) {
        when (sideEffect) {
            is CatProfileSideEffects.NavigateToCatPicker -> {
                navigateTo(
                    CatPickerFragment.newInstance(sideEffect.catName, sideEffect.catAge),
                    sideEffect.backStack
                )
            }
        }
    }

    override fun onViewReady() {
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            editTextCatName.doOnTextChanged { text, _, _, _ ->
                viewModel.setEvent(CatProfileEvents.OnCatNameChanged(text.toString()))
            }
            editTextCatAge.doOnTextChanged { text, _, _, _ ->
                viewModel.setEvent(CatProfileEvents.OnCatAgeChanged(text.toString()))
            }
            buttonContinue.setOnClickListener {
                viewModel.setEvent(CatProfileEvents.ButtonContinueClick)
            }
        }
    }

    companion object {
        fun newInstance() = CatProfileFragment()
    }

}