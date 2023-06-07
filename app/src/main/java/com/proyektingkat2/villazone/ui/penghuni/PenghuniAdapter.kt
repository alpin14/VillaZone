package com.proyektingkat2.villazone.ui.penghuni

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.proyektingkat2.villazone.databinding.PenghuniLayoutAdapterBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniFragmentDirections
import java.util.*

class PenghuniAdapter : RecyclerView.Adapter<PenghuniAdapter.PenghuniViewHolder>() {

    class PenghuniViewHolder(val itemBinding: PenghuniLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback =
        object : DiffUtil.ItemCallback<PenghuniEntity>() {
            override fun areItemsTheSame(oldItem: PenghuniEntity, newItem: PenghuniEntity): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.namaPenghuni == newItem.namaPenghuni &&
                        oldItem.nomorKamar == newItem.nomorKamar
                        oldItem.biayaKamar == newItem.biayaKamar
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

        holder.itemBinding.tvNamaPenghuni.text = currentPenghuni.namaPenghuni
        holder.itemBinding.tvNomorKamar.text = currentPenghuni.nomorKamar.toString()
        holder.itemView.setOnClickListener { view ->
            val direction = DaftarPenghuniFragmentDirections
                .actionDaftarPenghuniFragmentToTambahPenghuniFragment()
            view.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
