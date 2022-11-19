package com.limallucas96.feature_home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIFragment
import com.limallucas96.feature_home.databinding.FragmentHomeBinding
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseMVIFragment<FragmentHomeBinding, HomeFragmentAction, HomeFragmentViewState, HomeFragmentSideEffect>() {

    @set:Inject
    lateinit var featureOneNavigator: FeatureOneNavigator

    @set:Inject
    lateinit var featureTwoNavigator: FeatureTwoNavigator

    override val viewModel: HomeFragmentViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun renderViewState(viewState: HomeFragmentViewState) {
        binding.textViewPetCounter.text = viewState.petCounter
    }

    override fun handleSideEffect(sideEffect: HomeFragmentSideEffect) {
        when (sideEffect) {
            HomeFragmentSideEffect.NavigateToFeatureOne -> {
                activity?.let { startActivity(featureOneNavigator.newIntent(it)) }

            }
            HomeFragmentSideEffect.NavigateToFeatureTwo -> {
                activity?.let { startActivity(featureTwoNavigator.newIntent(it)) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        viewModel.dispatch(HomeFragmentAction.OnCreate)
    }


    private fun setupListeners() {
        binding.run {
            buttonFeatureOne.setOnClickListener {
                viewModel.dispatch(HomeFragmentAction.PrimaryButtonClick)
            }
            buttonFeatureTwo.setOnClickListener {
                viewModel.dispatch(HomeFragmentAction.SecondaryButtonClick)
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}