package com.proyektingkat2.villazone.ui.tagihan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyektingkat2.villazone.databinding.FragmentTagihanBinding
import com.proyektingkat2.villazone.model.Tagihan

class TagihanFragment : Fragment() {

    private lateinit var binding: FragmentTagihanBinding
    private lateinit var tagihanAdapter: TagihanAdapter
    private lateinit var tagihanViewModel: TagihanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTagihanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tagihanAdapter = TagihanAdapter()
        binding.recyclerView.adapter = tagihanAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        tagihanViewModel = ViewModelProvider(this).get(TagihanViewModel::class.java)
        tagihanViewModel.getTagihanList().observe(viewLifecycleOwner) { tagihanList ->
            tagihanAdapter.setDataList(tagihanList)
            checkEmptyViewVisibility(tagihanList)
        }

        tagihanViewModel.loadTagihanData()
    }

    private fun checkEmptyViewVisibility(tagihanList: List<Tagihan>) {
        if (tagihanList.isEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}
