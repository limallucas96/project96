package com.limallucas96.core_presentation.mvi

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.limallucas96.core_presentation.base.BaseFragment

abstract class BaseMVIFragment<VB : ViewBinding, UserAction : ViewAction, UIViewState : ViewState, UISideEffect : SideEffect> :
    BaseFragment<VB>() {

    protected abstract val viewModel: BaseMVIViewModel<UserAction, UIViewState, UISideEffect>

    protected abstract fun renderViewState(viewState: UIViewState)

    protected abstract fun handleSideEffect(sideEffect: UISideEffect)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSideEffect()
        observeViewState()
    }

    private fun observeSideEffect() {
        lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { sideEffect ->
                handleSideEffect(sideEffect)
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect { viewState ->
                renderViewState(viewState)
            }
        }
    }

}