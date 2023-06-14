package com.proyektingkat2.villazone.ui.tagihan.daftartagihan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyektingkat2.villazone.databinding.FragmentDaftarTagihanBinding
import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.repository.PenghuniRepository
import com.proyektingkat2.villazone.ui.tagihan.TagihanAdapter

class TagihanFragment : Fragment() {

    private lateinit var binding: FragmentDaftarTagihanBinding
    private lateinit var viewModel: TagihanViewModel
    private lateinit var tagihanAdapter: TagihanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaftarTagihanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tagihanAdapter = TagihanAdapter(this)

        binding.tagihanRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tagihanAdapter
        }

        val penghuniRepository = PenghuniRepository(AppDatabase.getInstance(requireContext()))
        val viewModelFactory = TagihanViewModelFactory(penghuniRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TagihanViewModel::class.java)

        viewModel.getAllTagihan().observe(viewLifecycleOwner, Observer { tagihanList ->
            Log.d("TagihanFragment", "Tagihan list size: ${tagihanList.size}")
            tagihanAdapter.setData(tagihanList)
            updateUI(tagihanList)
        })
    }

    private fun updateUI(penghuniEntity: List<PenghuniEntity>) {
        if (penghuniEntity.isNotEmpty()) {
            binding.cardView.visibility = View.GONE
            binding.tagihanRecyclerView.visibility = View.VISIBLE
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.tagihanRecyclerView.visibility = View.GONE
        }
    }
}


