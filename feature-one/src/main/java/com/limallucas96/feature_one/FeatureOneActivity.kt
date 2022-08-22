package com.limallucas96.feature_one

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIActivity
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewState
import com.limallucas96.feature_one.databinding.ActivityFeatureOneBinding

class FeatureOneActivity :
    BaseMVIActivity<ActivityFeatureOneBinding, FeatureOneEvents, FeatureOneViewState, FeatureOneSideEffects>() {

    override val viewModel: FeatureOneViewModel by viewModels()

    override fun inflateBinding() = ActivityFeatureOneBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setEvent(FeatureOneEvents.ViewScreen)
    }

    override fun onViewStateUpdated(viewState: FeatureOneViewState) {
        binding.textViewFeatureOne.text = viewState.screenText
    }

    override fun onSideEffectReceived(sideEffect: SideEffect) {
        when (sideEffect) {
            is FeatureOneSideEffects.ShowToast -> showToast()
        }
    }

    private fun showToast() {
        Toast.makeText(this, "Feature one toast", Toast.LENGTH_SHORT).show()
    }

}