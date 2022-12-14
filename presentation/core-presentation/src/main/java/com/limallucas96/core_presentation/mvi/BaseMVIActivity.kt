package com.limallucas96.core_presentation.mvi

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.limallucas96.core_presentation.base.BaseActivity

abstract class BaseMVIActivity<VB : ViewBinding, UIEvent : ViewAction, UIViewState : ViewState, UISideEffect : SideEffect> :
    BaseActivity<VB>() {

    protected abstract val viewModel: BaseMVIViewModel<UIEvent, UIViewState, UISideEffect>

    protected abstract fun onViewStateUpdated(viewState: UIViewState)

    protected abstract fun onSideEffectReceived(sideEffect: UISideEffect)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSideEffect()
        observeViewState()
    }

    private fun observeSideEffect() {
        lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { sideEffect ->
                onSideEffectReceived(sideEffect)
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { viewState ->
                onViewStateUpdated(viewState)
            }
        }
    }

}