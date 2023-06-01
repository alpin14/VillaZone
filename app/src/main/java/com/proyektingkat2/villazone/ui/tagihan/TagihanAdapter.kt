package com.proyektingkat2.villazone.ui.tagihan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.databinding.ListTagihanBinding
import com.proyektingkat2.villazone.model.Tagihan

class TagihanAdapter : RecyclerView.Adapter<TagihanAdapter.ViewHolder>() {
    private val tagihanList: MutableList<Tagihan> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListTagihanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tagihan = tagihanList[position]
        holder.bind(tagihan)
    }

    override fun getItemCount(): Int = tagihanList.size

    fun setDataList(newList: List<Tagihan>) {
        tagihanList.clear()
        tagihanList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListTagihanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tagihan: Tagihan) {
            binding.iconProfile.setImageResource(tagihan.gambar)
            binding.namaTextView.text = tagihan.nama
            binding.tvHarga.text = tagihan.biayaKamar
            binding.tvLunas.text = tagihan.statusPembayaran
            binding.nomorKamar.text = tagihan.nomorKamar.toString()
        }
    }
}
