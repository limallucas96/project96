package com.limallucas96.core_presentation.mvi

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.limallucas96.core_presentation.base.BaseFragment

abstract class BaseMVIFragment<VB : ViewBinding, UIEvent : ViewEvent, UIViewState : ViewState, UISideEffect : SideEffect> :
    BaseFragment<VB>() {

//    protected abstract val viewModel: BaseMVIViewModel<UIEvent, UIViewState, UISideEffect>

    protected abstract fun onViewStateUpdated(viewState: UIViewState)

    protected abstract fun onSideEffectReceived(sideEffect: SideEffect)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSideEffects()
        observeViewState()
    }

    private fun observeSideEffects() {
        lifecycleScope.launchWhenStarted {
//            viewModel.sideEffect.collect { sideEffect ->
//                onSideEffectReceived(sideEffect)
//            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenStarted {
//            viewModel.viewState.collect { viewState ->
//                onViewStateUpdated(viewState)
//            }
        }
    }

}