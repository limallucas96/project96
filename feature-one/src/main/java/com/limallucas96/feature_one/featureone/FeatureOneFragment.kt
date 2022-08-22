package com.limallucas96.feature_one.featureone

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.limallucas96.core_presentation.mvi.BaseMVIFragment
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.feature_one.databinding.FragmentFeatureOneBinding

class FeatureOneFragment :
    BaseMVIFragment<FragmentFeatureOneBinding, FeatureOneEvents, FeatureOneViewState, FeatureOneSideEffects>() {

//    override val viewModel: FeatureOneViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFeatureOneBinding {
        return FragmentFeatureOneBinding.inflate(inflater, container, false)
    }

    override fun onViewStateUpdated(viewState: FeatureOneViewState) {
        binding.textViewFeatureOne.text = viewState.screenText
    }

    override fun onSideEffectReceived(sideEffect: SideEffect) {
        when (sideEffect) {
            is FeatureOneSideEffects.ShowToast -> showToast()
        }
    }

    override fun onViewReady() {
        showToast()
    }

    private fun showToast() {
        activity?.let {
            Toast.makeText(it, "Feature one toast", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = FeatureOneFragment()
    }

}