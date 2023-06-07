package com.proyektingkat2.villazone.ui.penghuni.tambahpenghuni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.proyektingkat2.villazone.databinding.FragmentTambahPenghuniBinding
import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModel
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModelFactory

class TambahPenghuniFragment : Fragment() {

    private lateinit var binding: FragmentTambahPenghuniBinding
    private lateinit var viewModel: DaftarPenghuniViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTambahPenghuniBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = AppDatabase.getInstance(requireContext())
        val factory = DaftarPenghuniViewModelFactory(db.dao)
        viewModel = ViewModelProvider(requireActivity(), factory).get(DaftarPenghuniViewModel::class.java)

        binding.btnSimpan.setOnClickListener {
            val namaPenghuni = binding.namaPenghuniInp.text.toString().trim()
            val nomorKamar = binding.nomorKamarInp.text.toString().trim()
            val biayaKamar = binding.biayaKamarInp.text.toString().trim()
            val nomorPenghuni = binding.nomorPenghuniInp.text.toString().trim()

            if (namaPenghuni.isEmpty() || nomorKamar.isEmpty() || biayaKamar.isEmpty() || nomorPenghuni.isEmpty()) {
                Toast.makeText(requireContext(), "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                val penghuni = PenghuniEntity(
                    namaPenghuni = namaPenghuni,
                    nomorHp = nomorPenghuni,
                    nomorKamar = nomorKamar.toInt(),
                    biayaKamar = biayaKamar.toInt(),
                    tanggalMasuk = ""
                )

                viewModel.insertPenghuni(penghuni)

                Toast.makeText(requireContext(), "Penghuni ditambahkan", Toast.LENGTH_SHORT).show()

                binding.namaPenghuniInp.text.clear()
                binding.nomorKamarInp.text.clear()
                binding.biayaKamarInp.text.clear()
                binding.nomorPenghuniInp.text.clear()
            }
        }
    }
}

