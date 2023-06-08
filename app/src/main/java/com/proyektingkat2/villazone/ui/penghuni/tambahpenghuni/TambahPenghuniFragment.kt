package com.proyektingkat2.villazone.ui.penghuni.tambahpenghuni

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.proyektingkat2.villazone.MainActivity
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.databinding.FragmentTambahPenghuniBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.helper.toast
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModel

class TambahPenghuniFragment : Fragment(R.layout.fragment_tambah_penghuni) {

    private var _binding: FragmentTambahPenghuniBinding? = null
    private val binding get() = _binding!!
    private lateinit var daftarPenghuniViewModel: DaftarPenghuniViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTambahPenghuniBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daftarPenghuniViewModel = (activity as MainActivity).daftarPenghuniViewModel
        mView = view

        binding.btnSimpan.setOnClickListener {
            savePenghuni()
        }

        binding.btnReset.setOnClickListener {
            resetPenghuni()
        }
    }

    private fun savePenghuni() {
        val namaPenghuni = binding.namaPenghuniInp.text.toString().trim()
        val nomorPenghuni = binding.nomorPenghuniInp.text.toString().trim()
        val noKamarPenghuni = binding.nomorKamarInp.text.toString().trim()
        val biayaKamar = binding.biayaKamarInp.text.toString().trim()
        val tanggalMasuk = binding.tanggalMasukInp.text.toString().trim()

        if (namaPenghuni.isNotEmpty() && nomorPenghuni.isNotEmpty() && noKamarPenghuni.isNotEmpty() && biayaKamar.isNotEmpty() && tanggalMasuk.isNotEmpty()) {
            val penghuniEntity = PenghuniEntity(
                0,
                namaPenghuni,
                nomorPenghuni,
                noKamarPenghuni.toInt(),
                biayaKamar.toDouble(),
                tanggalMasuk
            )

            daftarPenghuniViewModel.addPenghuni(penghuniEntity)
            Snackbar.make(
                mView, "Penghuni berhasil disimpan",
                Snackbar.LENGTH_SHORT
            ).show()
            mView.findNavController()
                .navigate(R.id.action_tambahPenghuniFragment_to_daftarPenghuniFragment)
        } else {
            activity?.toast("Silahkan isi form yang masih kosong")
        }
    }

    private fun resetPenghuni() {
        binding.namaPenghuniInp.text.clear()
        binding.nomorKamarInp.text.clear()
        binding.biayaKamarInp.text.clear()
        binding.nomorPenghuniInp.text.clear()
        binding.tanggalMasukInp.text.clear()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


