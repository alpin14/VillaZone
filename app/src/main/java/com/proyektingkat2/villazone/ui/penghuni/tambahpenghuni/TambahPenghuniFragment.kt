package com.proyektingkat2.villazone.ui.penghuni.tambahpenghuni

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.proyektingkat2.villazone.MainActivity
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.databinding.FragmentTambahPenghuniBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.helper.toast
import com.proyektingkat2.villazone.model.StatusPembayaran
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModel
import java.text.DecimalFormat

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

        setupBiayaKamarInputFormatting()

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
        val biayaKamarString = binding.biayaKamarInp.text.toString().replace(".", "").replace(",", "")
        val biayaKamar = biayaKamarString.toIntOrNull()
        val tanggalMasuk = binding.tanggalMasukInp.text.toString().trim()

        if (namaPenghuni.isEmpty() || nomorPenghuni.isEmpty() || noKamarPenghuni.isEmpty() || biayaKamarString.isEmpty() || tanggalMasuk.isEmpty()) {
            activity?.toast("Silakan isi semua field yang kosong")
            return
        }

        if (tanggalMasuk.length != 10) {
            activity?.toast("Format tanggal masuk tidak valid")
            return
        }

        if (biayaKamar == null) {
            activity?.toast("Input biaya kamar tidak valid")
            return
        }

        // Lanjutkan proses simpan penghuni jika semua validasi berhasil
        val penghuniEntity = PenghuniEntity(
            0,
            namaPenghuni,
            nomorPenghuni,
            noKamarPenghuni.toInt(),
            biayaKamar.toString(),
            tanggalMasuk,
            statusPembayaran = StatusPembayaran.BELUM_LUNAS
        )

        daftarPenghuniViewModel.addPenghuni(penghuniEntity)
        Snackbar.make(
            mView, "Penghuni berhasil disimpan",
            Snackbar.LENGTH_SHORT
        ).show()
        mView.findNavController()
            .navigate(R.id.action_tambahPenghuniFragment_to_daftarPenghuniFragment)
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

    private fun setupBiayaKamarInputFormatting() {
        binding.biayaKamarInp.addTextChangedListener(object : TextWatcher {
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
                        binding.biayaKamarInp.removeTextChangedListener(this)
                        binding.biayaKamarInp.setText(formattedInput)
                        binding.biayaKamarInp.setSelection(formattedInput.length)
                        binding.biayaKamarInp.addTextChangedListener(this)
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


