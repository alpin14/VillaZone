package com.proyektingkat2.villazone.ui.tagihan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.databinding.TagihanLayoutAdapterBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.model.StatusPembayaran
import com.proyektingkat2.villazone.ui.tagihan.daftartagihan.TagihanFragmentDirections

class TagihanAdapter(private val fragment: Fragment) : RecyclerView.Adapter<TagihanAdapter.TagihanViewHolder>() {
    private val tagihanList: MutableList<PenghuniEntity> = mutableListOf()

    fun setData(data: List<PenghuniEntity>) {
        tagihanList.clear()
        tagihanList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagihanViewHolder {
        val binding =
            TagihanLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val tagihan = tagihanList[position]
                    showDetailTagihan(tagihan)
                }
            }
        }

        fun bind(tagihan: PenghuniEntity) {
            binding.tvNamaPenghuniT.text = tagihan.namaPenghuni
            binding.tvBiayaKamarT.text = tagihan.biayaKamar

            val paymentStatus = when (tagihan.statusPembayaran) {
                StatusPembayaran.LUNAS -> {
                    binding.tvStatusPembayaran.setTextColor(ContextCompat.getColor(itemView.context, R.color.green_save))
                    "Lunas"
                }
                else -> {
                    binding.tvStatusPembayaran.setTextColor(ContextCompat.getColor(itemView.context, R.color.red_delete))
                    "Belum Lunas"
                }
            }

            binding.tvStatusPembayaran.text = paymentStatus
        }


        private fun showDetailTagihan(tagihan: PenghuniEntity) {
            val action = TagihanFragmentDirections.actionTagihanFragmentToDetailTagihanFragment(tagihan)
            fragment.findNavController().navigate(action)
        }
    }
}