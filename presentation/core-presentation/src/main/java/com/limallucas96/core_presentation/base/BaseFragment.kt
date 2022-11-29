package com.limallucas96.core_presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    protected val binding: VB
        get() = _binding
            ?: throw IllegalStateException("reference to binding made before onCreateView or after onDestroyView.")


    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    open fun onBackPressedCalled(performBackPress: Boolean) {
        if (performBackPress) activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding(inflater, container)
        setupOnBackPressedDispatcher()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        onBackPressedCallback.remove()
        super.onDestroyView()
    }

    private fun setupOnBackPressedDispatcher() {
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                onBackPressedCalled(true)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

}