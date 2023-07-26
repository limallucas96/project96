package com.limallucas96.feature_home.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.limallucas96.core_presentation.mvi.BaseMVIFragment
import com.limallucas96.feature_home.databinding.FragmentHomeBinding
import com.limallucas96.navigator.featureone.FeatureOneNavigator
import com.limallucas96.navigator.featuretwo.FeatureTwoNavigator
import com.limallucas96.uikit.extensions.showAppDialog
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

    private lateinit var uriAdapter : UriAdapter
    private lateinit var bitmapAdapter : BitmapAdapter

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(6)) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            binding.cropImageView.setImageUriAsync(uri.first())
            uriAdapter.uris.clear()
            uriAdapter.uris.addAll(uri)
            uriAdapter.notifyDataSetChanged()

            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun renderViewState(viewState: HomeFragmentViewState) {
//        binding.run {
//            textViewPetCounter.text = viewState.petCounter
//            textViewLastCat.text = viewState.lastPet
//            textViewLastCat.isVisible = viewState.showLastPet
//        }
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
//        setupListeners()
        viewModel.dispatch(HomeFragmentAction.OnCreate)

        uriAdapter = UriAdapter {
            binding.cropImageView.setImageUriAsync(it)
        }
        binding.recyclerView.adapter = uriAdapter

        bitmapAdapter = BitmapAdapter {  }
        binding.recyclerViewResult.adapter = bitmapAdapter

        binding.buttonFeatureOne.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.buttonFeatureTwo.setOnClickListener {
            val croppedImage = binding.cropImageView.getCroppedImage()!!
            bitmapAdapter.bitmaps.addAll(listOf(croppedImage))
            bitmapAdapter.notifyDataSetChanged()
        }
        binding.textViewUpload.setOnClickListener {
            activity?.showAppDialog("Sending photos", "Sending photos. Wait", "")
        }
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