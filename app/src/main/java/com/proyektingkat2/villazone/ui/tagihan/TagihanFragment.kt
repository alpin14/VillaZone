package com.proyektingkat2.villazone.ui.tagihan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyektingkat2.villazone.databinding.FragmentTagihanBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.repository.PenghuniRepository

class TagihanFragment : Fragment() {

    private var _binding: FragmentTagihanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTagihanBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Initialize the ViewModel
//        val repository = PenghuniRepository() // Initialize your repository
//        val viewModelFactory = TagihanViewModelFactory(repository)
//        val tagihanViewModel = ViewModelProvider(this, viewModelFactory).get(TagihanViewModel::class.java)
//
//        // Initialize the RecyclerView adapter
//        val tagihanAdapter = TagihanAdapter()
//
//        // Set up the RecyclerView
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = tagihanAdapter
//        }
//
//        // Observe the LiveData from ViewModel
//        tagihanViewModel.penghuniList.observe(viewLifecycleOwner) { penghuniList ->
//            updateUI(penghuniList)
//        }
//    }
//
//    private fun updateUI(penghuniList: List<PenghuniEntity>) {
//        if (penghuniList.isNotEmpty()) {
//            binding.cardView.visibility = View.GONE
//            binding.recyclerView.visibility = View.VISIBLE
//            tagihanAdapter.submitList(penghuniList)
//        } else {
//            binding.cardView.visibility = View.VISIBLE
//            binding.recyclerView.visibility = View.GONE
//        }
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}

