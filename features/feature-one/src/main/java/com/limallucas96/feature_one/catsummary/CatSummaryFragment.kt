package com.limallucas96.feature_one.catsummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIFragment
import com.limallucas96.feature_one.R
import com.limallucas96.feature_one.catprofile.CatProfileFragment
import com.limallucas96.feature_one.databinding.FragmentCatSummaryBinding
import com.limallucas96.feature_one.entrypoint.FeatureOneEntryPointAction
import com.limallucas96.feature_one.entrypoint.FeatureOneEntryPointViewModel
import com.limallucas96.feature_one.enums.CatProfileProgress
import com.limallucas96.navigator.extensions.redirectToActivity
import com.limallucas96.navigator.featurehome.FeatureHomeNavigator
import com.limallucas96.navigator.fragment.FragmentNavigator
import com.limallucas96.uikit.extensions.argument
import com.limallucas96.uikit.extensions.loadUrl
import com.limallucas96.uikit.extensions.showAppDialog
import com.limallucas96.uikit.extensions.withArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CatSummaryFragment :
    BaseMVIFragment<FragmentCatSummaryBinding, CatSummaryAction, CatSummaryViewState, CatSummarySideEffect>() {

    @set:Inject
    lateinit var navigator: FragmentNavigator

    @set:Inject
    lateinit var featureHomeNavigator: FeatureHomeNavigator

    override val viewModel: CatSummaryViewModel by viewModels()

    // TODO Check if there is a smarter way to put this into base
    private val activityViewModel: FeatureOneEntryPointViewModel by activityViewModels()

    private val catName: String by argument(ARG_CAT_NAME) { "" }
    private val catAge: String by argument(ARG_CAT_AGE) { "" }
    private val catPhotoUrl: String by argument(ARG_CAT_PHOTO_URL) { "" }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatSummaryBinding {
        return FragmentCatSummaryBinding.inflate(inflater, container, false)
    }

    override fun renderViewState(viewState: CatSummaryViewState) {
        binding.run {
            textViewCatName.text = viewState.catName
            textViewCatAge.text = viewState.catAge
            activity?.let { context -> imageViewCat.loadUrl(context, viewState.catPhotoUrl) }
        }
    }

    override fun handleSideEffect(sideEffect: CatSummarySideEffect) {
        when (sideEffect) {
            CatSummarySideEffect.NavigateToHome -> {
                activity?.let { it.redirectToActivity(featureHomeNavigator.newIntent(it), finish = true) }
            }
            is CatSummarySideEffect.NavigateToCatProfile -> {
                navigator.navigateTo(
                    activity,
                    CatProfileFragment.newInstance(),
                    clearStack = sideEffect.clearBackStack
                )
            }
            CatSummarySideEffect.ShowExitConfirmationDialog -> {
                showExitConfirmationDialog()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        activityViewModel.dispatch(FeatureOneEntryPointAction.UpdateToolbar(CatProfileProgress.CAT_SUMMARY_STEP))
        viewModel.dispatch(CatSummaryAction.OnCreate(catName, catAge, catPhotoUrl))
    }

    override fun onBackPressedCalled(performBackPress: Boolean) {
        super.onBackPressedCalled(performBackPress)
        activityViewModel.dispatch(FeatureOneEntryPointAction.UpdateToolbar(CatProfileProgress.CAT_PICKER_STEP))
    }

    private fun setupListeners() {
        binding.run {
            buttonGoToHome.setOnClickListener {
                viewModel.dispatch(CatSummaryAction.ButtonGoToHomeClick)
            }
            buttonReDoMyCat.setOnClickListener {
                viewModel.dispatch(CatSummaryAction.ButtonGoToCatProfileClick)
            }
            buttonSaveLocally.setOnClickListener {
                viewModel.dispatch(CatSummaryAction.ButtonSaveLocallyClick)
            }
        }
    }

    private fun showExitConfirmationDialog() {
        activity?.showAppDialog(
            title = getString(R.string.cat_summary_fragment_exit_dialog_title),
            body = getString(R.string.cat_summary_fragment_exit_dialog_body),
            positiveText = getString(R.string.cat_summary_fragment_exit_dialog_positive_button),
            isCancelable = false,
            positiveAction = { viewModel.dispatch(CatSummaryAction.DialogConfirmExitClick) }
        )
    }

    companion object {
        private const val ARG_CAT_NAME = "ARG_CAT_NAME"
        private const val ARG_CAT_AGE = "ARG_CAT_AGE"
        private const val ARG_CAT_PHOTO_URL = "ARG_CAT_PHOTO_URL"

        fun newInstance(
            catName: String,
            catAge: String,
            catPhotoUrl: String
        ) = CatSummaryFragment().withArgs {
            putString(ARG_CAT_NAME, catName)
            putString(ARG_CAT_AGE, catAge)
            putString(ARG_CAT_PHOTO_URL, catPhotoUrl)
        }

    }
}