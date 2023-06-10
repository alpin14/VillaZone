package com.proyektingkat2.villazone.ui.tagihan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.proyektingkat2.villazone.databinding.FragmentTagihanBinding
import com.proyektingkat2.villazone.db.PenghuniEntity

class TagihanFragment : Fragment() {

    private var _binding: FragmentTagihanBinding? = null
    private val binding get() = _binding!!

    private val args: TagihanFragmentArgs by navArgs()
    private lateinit var currentPenghuni: PenghuniEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTagihanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentPenghuni = args.penghuni!!

        displayTagihanDetails()

        binding.btnBayar.setOnClickListener {
            markTagihanAsLunas()
        }
    }

    private fun displayTagihanDetails() {
        binding.namaPenghuniText.text = currentPenghuni.namaPenghuni
        val biayaKamarFormatted = formatCurrency(currentPenghuni.biayaKamar)
        binding.biayaKamarText.text = biayaKamarFormatted
        binding.statusPembayaranText.text = getStatusPembayaranText(currentPenghuni.statusPembayaran)
    }

    private fun markTagihanAsLunas() {
        // Update status pembayaran penghuni menjadi "Lunas" di database
        // ...
        // Tampilkan toast atau pesan sukses
    }

    private fun getStatusPembayaranText(statusPembayaran: String): String {
        return if (statusPembayaran == "Lunas") {
            "Status Pembayaran: Lunas"
        } else {
            "Status Pembayaran: Belum Lunas"
        }
    }

    private fun formatCurrency(input: Double): String {
        // Lakukan formatting angka ke dalam format mata uang yang diinginkan
        // ...
        return formattedValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

