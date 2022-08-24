package com.limallucas96.feature_one.catsummary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.feature_one.databinding.FragmentCatSummaryBinding
import com.limallucas96.feature_one.catprofile.CatProfileFragment
import com.limallucas96.uikit.extensions.argument
import com.limallucas96.uikit.extensions.loadUrl
import com.limallucas96.uikit.extensions.withArgs

class CatSummaryFragment :
    BaseMVINavigationFragment<FragmentCatSummaryBinding, CatSummaryEvents, CatSummaryViewState, CatSummarySideEffects>() {

    override val viewModel: CatSummaryViewModel by viewModels()

    private val catName: String by argument(ARG_CAT_NAME) { "" }
    private val catAge: String by argument(ARG_CAT_AGE) { "" }
    private val catPhotoUrl: String by argument(ARG_CAT_PHOTO_URL) { "" }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatSummaryBinding {
        return FragmentCatSummaryBinding.inflate(inflater, container, false)
    }

    override fun onViewStateUpdated(viewState: CatSummaryViewState) {
        binding.run {
            textViewCatSummary.text = viewState.catSummary
            activity?.let { context -> imageViewCat.loadUrl(context, viewState.catPhotoUrl) }
        }
    }

    override fun onSideEffectReceived(sideEffect: CatSummarySideEffects) {
        when (sideEffect) {
            CatSummarySideEffects.NavigateToHome -> {
                // TODO Navigation to home or splash
                Toast.makeText(requireContext(), "NavigateToHome", Toast.LENGTH_SHORT).show()
            }
            is CatSummarySideEffects.NavigateToCatProfile -> {
                navigateTo(
                    CatProfileFragment.newInstance(),
                    clearStack = sideEffect.clearBackStack
                )
            }
        }
    }

    override fun onViewReady() {
        setupListeners()
        viewModel.setEvent(CatSummaryEvents.ViewScreen(catName, catAge, catPhotoUrl))
    }

    private fun setupListeners() {
        binding.run {
            buttonGoToHome.setOnClickListener {
                viewModel.setEvent(CatSummaryEvents.ButtonGoToHomeClick)
            }
            buttonReDoMyCat.setOnClickListener {
                viewModel.setEvent(CatSummaryEvents.ButtonGoToCatProfileClick)
            }
        }
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