package com.proyektingkat2.villazone.ui.penghuni

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.databinding.PenghuniLayoutAdapterBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniFragmentDirections

class PenghuniAdapter : RecyclerView.Adapter<PenghuniAdapter.PenghuniViewHolder>() {

    class PenghuniViewHolder(val itemBinding: PenghuniLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback = object : DiffUtil.ItemCallback<PenghuniEntity>() {
        override fun areItemsTheSame(oldItem: PenghuniEntity, newItem: PenghuniEntity): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.namaPenghuni == newItem.namaPenghuni &&
                    oldItem.nomorKamar == newItem.nomorKamar
        }

        override fun areContentsTheSame(oldItem: PenghuniEntity, newItem: PenghuniEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenghuniViewHolder {
        return PenghuniViewHolder(
            PenghuniLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PenghuniViewHolder, position: Int) {
        val currentPenghuni = differ.currentList[position]

        holder.itemBinding.tvNamaPenghuni.text = "Nama: ${currentPenghuni.namaPenghuni}"
        holder.itemBinding.tvNomorKamar.text = "Nomor Kamar: ${currentPenghuni.nomorKamar}"

        holder.itemView.setOnClickListener { view ->
            val direction = DaftarPenghuniFragmentDirections
                .actionDaftarPenghuniFragmentToDetailPenghuniFragment(currentPenghuni)
            view.findNavController().navigate(direction)
        }
    }



    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
