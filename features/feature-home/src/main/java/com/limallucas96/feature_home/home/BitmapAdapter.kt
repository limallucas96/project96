package com.limallucas96.feature_home.home

import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.limallucas96.feature_home.databinding.CropViewHolderBinding

class BitmapAdapter(private val onClickListener: (Uri) -> Unit ) : RecyclerView.Adapter<BitmapAdapter.TempAdapterViewHolder>() {

    var bitmaps: MutableList<Bitmap> = mutableListOf()

    inner class TempAdapterViewHolder(private val binding: CropViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bitmap: Bitmap) {
            binding.imageView.setImageBitmap(bitmap)
//            binding.imageView.setOnClickListener { onClickListener.invoke(bitmap) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempAdapterViewHolder {
        val binding = CropViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TempAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TempAdapterViewHolder, position: Int) {
        holder.bind(bitmaps[position])
    }

    override fun getItemCount() = bitmaps.size
}
