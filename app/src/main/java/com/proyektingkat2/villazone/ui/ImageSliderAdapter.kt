package com.proyektingkat2.villazone.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.databinding.ImageSlideBinding
import com.proyektingkat2.villazone.model.ImageData
import com.bumptech.glide.Glide

class ImageSliderAdapter(private val items: List<ImageData>) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: ImageSlideBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView

        fun bind(data: ImageData){
            with(binding){
                Glide.with(itemView)
                    .load(data.ImageUrl)
                    .into(ivSlider)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ImageSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}