package com.proyektingkat2.villazone.ui.tagihan.detailtagihan


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.proyektingkat2.villazone.databinding.FragmentDetailTagihanBinding
import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.model.StatusPembayaran
import com.proyektingkat2.villazone.repository.PenghuniRepository


class DetailTagihanFragment : Fragment() {

    private val args: DetailTagihanFragmentArgs by navArgs()
    private lateinit var viewModel: DetailTagihanViewModel

    private var _binding: FragmentDetailTagihanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailTagihanBinding.inflate(inflater, container, false)

        // Mengakses data tagihan dari argumen
        val tagihan = args.tagihan

        // Menampilkan data tagihan di layout detail tagihan
        binding.namaPenghuniText.text = tagihan.namaPenghuni
        binding.noKamarPenghuniText.text = tagihan.nomorKamar.toString()
        binding.biayaKamarText.text = tagihan.biayaKamar.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val penghuniRepository = PenghuniRepository(AppDatabase.getInstance(requireContext()))
        val viewModelFactory = DetailTagihanViewModelFactory(penghuniRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailTagihanViewModel::class.java)
        val tagihan = args.tagihan

        binding.btnLunas.setOnClickListener {
            viewModel.updateTagihanStatus(tagihanId = tagihan.id, StatusPembayaran.LUNAS)
            showToast("Status Pembayaran berhasil diperbarui")
            findNavController().popBackStack()
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}