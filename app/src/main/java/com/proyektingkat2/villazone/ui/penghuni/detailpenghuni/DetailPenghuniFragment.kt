package com.proyektingkat2.villazone.ui.penghuni.detailpenghuni

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.proyektingkat2.villazone.MainActivity
import com.proyektingkat2.villazone.databinding.FragmentDetailPenghuniBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModel

class DetailPenghuniFragment : Fragment() {

    private var _binding: FragmentDetailPenghuniBinding? = null
    private val binding get() = _binding!!

    private val args: DetailPenghuniFragmentArgs by navArgs()
    private lateinit var currentPenghuni: PenghuniEntity
    private lateinit var daftarPenghuniViewModel: DaftarPenghuniViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPenghuniBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daftarPenghuniViewModel = (activity as MainActivity).daftarPenghuniViewModel
        currentPenghuni = args.penghuni!!

        binding.namaPenghuniText.text = currentPenghuni.namaPenghuni
        binding.nomorPenghuniText.text = currentPenghuni.nomorHp
        binding.noKamarPenghuniText.text = currentPenghuni.nomorKamar.toString()
        binding.biayaKamarText.text = currentPenghuni.biayaKamar.toString()
        binding.tanggalMasukText.text = currentPenghuni.tanggalMasuk

        binding.btnUpdate.setOnClickListener {
            val nama = binding.namaPenghuniText.text.toString().trim()
            val noKamar = binding.noKamarPenghuniText.text.toString().trim()
            val biayaK = binding.biayaKamarText.text.toString().trim().toIntOrNull()
            val kontak = binding.nomorPenghuniText.text.toString().trim().toDoubleOrNull()
            val masuk = binding.tanggalMasukText.text.toString().trim()

            if (nama.isNotEmpty() && noKamar.isNotEmpty() && biayaK != null && kontak != null) {
                val updatedPenghuni = PenghuniEntity(
                    currentPenghuni.id,
                    nama,
                    noKamar,
                    biayaK,
                    kontak,
                    masuk
                )
                daftarPenghuniViewModel.updatePenghuni(updatedPenghuni)
                showToast("Data penghuni berhasil diperbarui")
                findNavController().popBackStack()
            } else {
                showToast("Harap lengkapi data dengan benar")
            }
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Hapus")
            .setMessage("Apakah Anda yakin ingin menghapus penghuni ini?")
            .setPositiveButton("Hapus") { _, _ ->
                daftarPenghuniViewModel.deletePenghuni(currentPenghuni)
                showToast("Data penghuni berhasil dihapus")
                findNavController().popBackStack()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
