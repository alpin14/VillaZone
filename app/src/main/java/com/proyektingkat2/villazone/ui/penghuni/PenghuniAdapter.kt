package com.proyektingkat2.villazone.ui.penghuni

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.databinding.ItemPenghuniBinding
import com.proyektingkat2.villazone.db.PenghuniEntity

class PenghuniAdapter : ListAdapter<PenghuniEntity, PenghuniAdapter.PenghuniViewHolder>(PenghuniDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenghuniViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPenghuniBinding.inflate(inflater, parent, false)
        return PenghuniViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PenghuniViewHolder, position: Int) {
        val penghuni = getItem(position)
        holder.bind(penghuni)
    }

    inner class PenghuniViewHolder(private val binding: ItemPenghuniBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(penghuni: PenghuniEntity) {
            binding.apply {
                titleTv.text = penghuni.namaPenghuni
                nomorKamar.text = penghuni.nomorKamar.toString()
            }
        }
    }

    private class PenghuniDiffCallback : DiffUtil.ItemCallback<PenghuniEntity>() {
        override fun areItemsTheSame(oldItem: PenghuniEntity, newItem: PenghuniEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PenghuniEntity, newItem: PenghuniEntity): Boolean {
            return oldItem == newItem
        }
    }
}
