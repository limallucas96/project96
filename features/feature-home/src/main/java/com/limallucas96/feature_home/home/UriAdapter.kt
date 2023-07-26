package com.limallucas96.feature_home.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.limallucas96.feature_home.databinding.CropViewHolderBinding

class UriAdapter(private val onClickListener: (Uri) -> Unit ) : RecyclerView.Adapter<UriAdapter.TempAdapterViewHolder>() {

    var uris: MutableList<Uri> = mutableListOf()

    inner class TempAdapterViewHolder(private val binding: CropViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bitmap: Uri) {
            binding.imageView.setImageURI(bitmap)
            binding.imageView.setOnClickListener { onClickListener.invoke(bitmap) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempAdapterViewHolder {
        val binding = CropViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TempAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TempAdapterViewHolder, position: Int) {
        holder.bind(uris[position])
    }

    override fun getItemCount() = uris.size
}
