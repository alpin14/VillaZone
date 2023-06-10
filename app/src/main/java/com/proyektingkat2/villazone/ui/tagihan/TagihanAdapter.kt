package com.proyektingkat2.villazone.ui.tagihan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.databinding.TagihanLayoutAdapterBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.model.StatusPembayaran

class TagihanAdapter : RecyclerView.Adapter<TagihanAdapter.TagihanViewHolder>() {

    private val tagihanList: MutableList<PenghuniEntity> = mutableListOf()

    fun setData(data: List<PenghuniEntity>) {
        tagihanList.clear()
        tagihanList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagihanViewHolder {
        val binding = TagihanLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagihanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagihanViewHolder, position: Int) {
        val tagihan = tagihanList[position]
        holder.bind(tagihan)
    }

    override fun getItemCount(): Int {
        return tagihanList.size
    }

    inner class TagihanViewHolder(private val binding: TagihanLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tagihan: PenghuniEntity) {
            binding.tvNamaPenghuniT.text = tagihan.namaPenghuni
            binding.tvBiayaKamarT.text = tagihan.biayaKamar.toString()
            binding.tvStatusPembayaran.text = when (tagihan.statusPembayaran) {
                StatusPembayaran.LUNAS -> "Lunas"
                StatusPembayaran.BELUM_LUNAS -> "Belum Lunas"
            }
        }
    }
}


