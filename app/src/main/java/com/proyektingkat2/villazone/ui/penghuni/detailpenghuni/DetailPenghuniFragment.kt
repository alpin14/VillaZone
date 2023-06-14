package com.proyektingkat2.villazone.ui.penghuni.detailpenghuni

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.proyektingkat2.villazone.helper.toast
import com.proyektingkat2.villazone.model.StatusPembayaran
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModel
import java.text.DecimalFormat

class DetailPenghuniFragment : Fragment() {

    private var _binding: FragmentDetailPenghuniBinding? = null
    private val binding get() = _binding!!

    private val args: DetailPenghuniFragmentArgs by navArgs()
    private lateinit var currentPenghuni: PenghuniEntity
    private lateinit var daftarPenghuniViewModel: DaftarPenghuniViewModel

    private var isEditModeEnabled = false

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

        setupBiayaKamarInputFormatting()

        displayPenghuniDetails()

        binding.namaPenghuniText.setOnClickListener { enableEditMode() }
        binding.nomorPenghuniText.setOnClickListener { enableEditMode() }
        binding.noKamarPenghuniText.setOnClickListener { enableEditMode() }
        binding.biayaKamarText.setOnClickListener { enableEditMode() }
        binding.tanggalMasukText.setOnClickListener { enableEditMode() }




        binding.btnUpdate.setOnClickListener {
            updatePenghuni()
            enableEditMode()
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun displayPenghuniDetails() {
        binding.biayaKamarText.setText(currentPenghuni.biayaKamar)
        binding.namaPenghuniText.setText(currentPenghuni.namaPenghuni)
        binding.nomorPenghuniText.setText(currentPenghuni.nomorHp)
        binding.noKamarPenghuniText.setText(currentPenghuni.nomorKamar.toString())
        binding.tanggalMasukText.setText(currentPenghuni.tanggalMasuk)
        Log.d("currentPenghuni", "Biaya kamar "+ currentPenghuni.biayaKamar)
    }

    private fun updatePenghuni() {
        val nama = binding.namaPenghuniText.text.toString().trim()
        val noKamar = binding.noKamarPenghuniText.text.toString().trim().toIntOrNull()
        val biayaKamarString = binding.biayaKamarText.text.toString().replace(".", "").replace(",", "")
        val biayaKamar = biayaKamarString.toIntOrNull()
        val nomorHpString = binding.nomorPenghuniText.text.toString().trim()
        val nomorHp = nomorHpString.toDoubleOrNull()
        val masuk = binding.tanggalMasukText.text.toString().trim()

        if (nama.isNotEmpty() && noKamar != null && biayaKamar != null && nomorHp != null) {
            val updatedPenghuni = PenghuniEntity(
                currentPenghuni.id,
                nama,
                nomorHpString,
                noKamar,
                biayaKamar.toString(),
                masuk,
                StatusPembayaran.BELUM_LUNAS
            )
            daftarPenghuniViewModel.updatePenghuni(updatedPenghuni)
            showToast("Data penghuni berhasil diperbarui")
            findNavController().popBackStack()
        } else {
            showErrorMessage("Harap lengkapi data dengan benar")
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

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun enableEditMode() {
        isEditModeEnabled = true
        binding.btnUpdate.text = "Simpan"
        binding.namaPenghuniText.isEnabled = true
        binding.nomorPenghuniText.isEnabled = true
        binding.noKamarPenghuniText.isEnabled = true
        binding.biayaKamarText.isEnabled = true
        binding.tanggalMasukText.isEnabled = true
    }


    private fun setupBiayaKamarInputFormatting() {
        binding.biayaKamarText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    val originalInput = s.toString()
                    val cleanInput = originalInput.replace(".", "")
                    val formattedInput = formatCurrency(cleanInput)
                    if (originalInput != formattedInput) {
                        binding.biayaKamarText.removeTextChangedListener(this)
                        binding.biayaKamarText.setText(formattedInput)
                        binding.biayaKamarText.setSelection(formattedInput.length)
                        binding.biayaKamarText.addTextChangedListener(this)
                    }
                }
            }
        })
    }

    private fun formatCurrency(input: String): String {
        return try {
            val cleanInput = input.replace(Regex("[^\\d]"), "") // Menghapus semua karakter non-digit
            val value = cleanInput.toLong()
            val formatter = DecimalFormat("#,###")
            formatter.format(value)
        } catch (e: NumberFormatException) {
            activity?.toast("Input tidak valid")
            ""
        }
    }
}
