package com.limallucas96.feature_one.catsummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVINavigationFragment
import com.limallucas96.feature_one.databinding.FragmentCatSummaryBinding
import com.limallucas96.feature_one.catprofile.CatProfileFragment
import com.limallucas96.uikit.extensions.argument
import com.limallucas96.uikit.extensions.loadUrl
import com.limallucas96.uikit.extensions.withArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatSummaryFragment :
    BaseMVINavigationFragment<FragmentCatSummaryBinding, CatSummaryAction, CatSummaryViewState, CatSummarySideEffect>() {

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

    override fun renderViewState(viewState: CatSummaryViewState) {
        binding.run {
            textViewCatSummary.text = viewState.catSummary
            activity?.let { context -> imageViewCat.loadUrl(context, viewState.catPhotoUrl) }
        }
    }

    override fun handleSideEffect(sideEffect: CatSummarySideEffect) {
        when (sideEffect) {
            CatSummarySideEffect.NavigateToHome -> {
                // TODO Navigation to home or splash
                Toast.makeText(requireContext(), "NavigateToHome", Toast.LENGTH_SHORT).show()
            }
            is CatSummarySideEffect.NavigateToCatProfile -> {
                navigateTo(
                    CatProfileFragment.newInstance(),
                    clearStack = sideEffect.clearBackStack
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        viewModel.dispatch(CatSummaryAction.ViewScreen(catName, catAge, catPhotoUrl))
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