package com.limallucas96.feature_one.catpicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIFragment
import com.limallucas96.feature_one.catsummary.CatSummaryFragment
import com.limallucas96.feature_one.databinding.FragmentCatPickerBinding
import com.limallucas96.navigator.fragment.FragmentNavigator
import com.limallucas96.uikit.extensions.argument
import com.limallucas96.uikit.extensions.loadUrl
import com.limallucas96.uikit.extensions.withArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CatPickerFragment :
    BaseMVIFragment<FragmentCatPickerBinding, CatPickerAction, CatPickerViewState, CatPickerSideEffect>() {

    @set:Inject
    lateinit var navigator: FragmentNavigator

    override val viewModel: CatPickerViewModel by viewModels()

    private val catName: String by argument(ARG_CAT_NAME) { "" }
    private val catAge: String by argument(ARG_CAT_AGE) { "" }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatPickerBinding {
        return FragmentCatPickerBinding.inflate(inflater, container, false)
    }

    override fun renderViewState(viewState: CatPickerViewState) {
        handleViewState(viewState)
        setupCatPhoto(viewState.catUrlPhoto)
    }

    override fun handleSideEffect(sideEffect: CatPickerSideEffect) {
        when (sideEffect) {
            is CatPickerSideEffect.NavigateToCatSummary -> {
                navigator.navigateTo(
                    activity,
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        viewModel.dispatch(CatPickerAction.InitView(catName, catAge))
        viewModel.dispatch(CatPickerAction.ViewScreen)
    }

    private fun setupListeners() {
        binding.run {
            buttonChooseCat.setOnClickListener {
                viewModel.dispatch(CatPickerAction.ButtonChooseCatClick)
            }
            buttonShortNewCat.setOnClickListener {
                viewModel.dispatch(CatPickerAction.ButtonShortNewCat)
            }
            textViewRetry.setOnClickListener {
                viewModel.dispatch(CatPickerAction.Retry)
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