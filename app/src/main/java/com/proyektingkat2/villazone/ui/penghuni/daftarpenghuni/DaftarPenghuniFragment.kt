package com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyektingkat2.villazone.databinding.FragmentDaftarPenghuniBinding
import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.ui.penghuni.PenghuniAdapter

class DaftarPenghuniFragment : Fragment() {

    private lateinit var binding: FragmentDaftarPenghuniBinding
    private lateinit var adapter: PenghuniAdapter
    private val viewModel: DaftarPenghuniViewModel by viewModels {
        val db = AppDatabase.getInstance(requireContext())
        DaftarPenghuniViewModelFactory(db.dao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaftarPenghuniBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi adapter
        adapter = PenghuniAdapter()

        // Set layout manager dan adapter pada RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observasi LiveData allPenghuniLiveData dari ViewModel
        viewModel.allPenghuniLiveData.observe(viewLifecycleOwner, Observer { penghuniList ->
            // Update data penghuni pada adapter
            adapter.submitList(penghuniList)
        })
    }
}
