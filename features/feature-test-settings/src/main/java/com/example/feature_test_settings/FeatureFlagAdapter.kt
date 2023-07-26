package com.example.feature_test_settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abtest.featureflags.Feature
import com.example.abtest.providers.FeatureFlagProvider
import com.example.feature_test_settings.databinding.ViewHolderTestSettingsBinding

class FeatureFlagAdapter<T : Feature>(
    private val items: Array<T>,
    private val provider: FeatureFlagProvider,
    private val checkedListener: Function2<Feature, Boolean, Unit>
) : RecyclerView.Adapter<FeatureFlagAdapter.FeatureFlagViewHolder<T>>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FeatureFlagViewHolder<T>, position: Int) {
        holder.bind(items[position], provider, checkedListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureFlagViewHolder<T> {
        val binding = ViewHolderTestSettingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeatureFlagViewHolder(binding)
    }

    class FeatureFlagViewHolder<T : Feature>(private val binding: ViewHolderTestSettingsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            feature: T,
            provider: FeatureFlagProvider,
            checkedListener: Function2<Feature, Boolean, Unit>
        ) {
            binding.textViewTitle.text = feature.title
            binding.textViewDescription.text = feature.explanation
            binding.switchCompat.isChecked = provider.isFeatureEnabled(feature)
            binding.switchCompat.setOnCheckedChangeListener { switch, isChecked ->
                if (switch.isPressed) checkedListener.invoke(feature, isChecked)
            }
        }
    }
}