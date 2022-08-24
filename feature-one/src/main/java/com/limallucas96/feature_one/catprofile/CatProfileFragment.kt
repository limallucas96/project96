package com.limallucas96.feature_one.catprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.feature_one.catpicker.CatPickerFragment
import com.limallucas96.feature_one.databinding.FragmentCatProfileBinding


class CatProfileFragment :
    BaseMVINavigationFragment<FragmentCatProfileBinding, CatProfileAction, CatProfileViewState, CatProfileSideEffect>() {

    override val viewModel: CatProfileViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatProfileBinding {
        return FragmentCatProfileBinding.inflate(inflater, container, false)
    }

    override fun renderViewState(viewState: CatProfileViewState) {
        binding.buttonContinue.isEnabled = viewState.isContinueButtonEnable
    }

    override fun handleSideEffect(sideEffect: CatProfileSideEffect) {
        when (sideEffect) {
            is CatProfileSideEffect.NavigateToCatPicker -> {
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
                viewModel.dispatch(CatProfileAction.OnCatNameChanged(text.toString()))
            }
            editTextCatAge.doOnTextChanged { text, _, _, _ ->
                viewModel.dispatch(CatProfileAction.OnCatAgeChanged(text.toString()))
            }
            buttonContinue.setOnClickListener {
                viewModel.dispatch(CatProfileAction.ButtonContinueClick)
            }
        }
    }

    companion object {
        fun newInstance() = CatProfileFragment()
    }

}