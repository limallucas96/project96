package com.limallucas96.feature_one.catpicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.feature_one.catsummary.CatSummaryFragment
import com.limallucas96.feature_one.databinding.FragmentCatPickerBinding
import com.limallucas96.uikit.extensions.argument
import com.limallucas96.uikit.extensions.loadUrl
import com.limallucas96.uikit.extensions.withArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatPickerFragment :
    BaseMVINavigationFragment<FragmentCatPickerBinding, CatPickerEvents, CatPickerViewState, CatPickerSideEffects>() {

    override val viewModel: CatPickerViewModel by viewModels()

    private val catName: String by argument(ARG_CAT_NAME) { "" }
    private val catAge: String by argument(ARG_CAT_AGE) { "" }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatPickerBinding {
        return FragmentCatPickerBinding.inflate(inflater, container, false)
    }

    override fun onViewStateUpdated(viewState: CatPickerViewState) {
        handleViewState(viewState)
        setupCatPhoto(viewState.catUrlPhoto)
    }

    override fun onSideEffectReceived(sideEffect: CatPickerSideEffects) {
        when (sideEffect) {
            is CatPickerSideEffects.NavigateToCatSummary -> {
                navigateTo(
                    CatSummaryFragment.newInstance(
                        sideEffect.catName,
                        sideEffect.catAge,
                        sideEffect.catPhotoUrl
                    ),
                    clearStack = sideEffect.clearBackStack
                )
            }
        }
    }

    override fun onViewReady() {
        setupListeners()
        viewModel.setEvent(CatPickerEvents.InitView(catName, catAge))
        viewModel.setEvent(CatPickerEvents.ViewScreen)
    }

    private fun setupListeners() {
        binding.run {
            buttonChooseCat.setOnClickListener {
                viewModel.setEvent(CatPickerEvents.ButtonChooseCatClick)
            }
            buttonShortNewCat.setOnClickListener {
                viewModel.setEvent(CatPickerEvents.ButtonShortNewCat)
            }
            textViewRetry.setOnClickListener {
                viewModel.setEvent(CatPickerEvents.Retry)
            }
        }
    }

    private fun handleViewState(viewState: CatPickerViewState) {
        binding.run {
            progressBarLoading.isVisible = viewState.isLoading
            groupContent.isVisible = viewState.isError.not() && viewState.isLoading.not()
            textViewRetry.isVisible = viewState.isError
        }
    }

    private fun setupCatPhoto(url: String) {
        activity?.let { context -> binding.imageViewCat.loadUrl(context, url) }
    }

    companion object {
        private const val ARG_CAT_NAME = "ARG_CAT_NAME"
        private const val ARG_CAT_AGE = "ARG_CAT_AGE"
        fun newInstance(
            catName: String,
            catAge: String,
        ) = CatPickerFragment().withArgs {
            putString(ARG_CAT_NAME, catName)
            putString(ARG_CAT_AGE, catAge)
        }
    }

}