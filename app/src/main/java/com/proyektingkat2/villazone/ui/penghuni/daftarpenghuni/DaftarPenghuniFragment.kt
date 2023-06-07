package com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.proyektingkat2.villazone.MainActivity
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.databinding.FragmentDaftarPenghuniBinding
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.ui.penghuni.PenghuniAdapter

class DaftarPenghuniFragment : Fragment(R.layout.fragment_daftar_penghuni),
        SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentDaftarPenghuniBinding? = null
    private val binding get() = _binding!!
    private lateinit var _daftarPenghuniViewModel: DaftarPenghuniViewModel
    private lateinit var penghuniAdapter: PenghuniAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDaftarPenghuniBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _daftarPenghuniViewModel = (activity as MainActivity).daftarPenghuniViewModel
        setUpRecyclerView()

        binding.fabAddPenghuni.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_daftarPenghuniFragment_to_tambahPenghuniFragment)
        }
    }

    private fun setUpRecyclerView() {
        penghuniAdapter = PenghuniAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = penghuniAdapter
        }

        activity?.let {
            _daftarPenghuniViewModel.getAllPenghuni().observe(viewLifecycleOwner, { penghuni ->
                penghuniAdapter.differ.submitList(penghuni)
                updateUI(penghuni)
            })
        }
    }

    private fun updateUI(penghuniEntity: List<PenghuniEntity>) {
        if (penghuniEntity.isNotEmpty()) {
            binding.cardView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as androidx.appcompat.widget.SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText != null) {
            searchPenghuni(newText)
        }
        return true
    }


    private fun searchPenghuni(query: String?) {
        val searchQuery = "%$query%"
        _daftarPenghuniViewModel.searchPenghuni(searchQuery).observe(
            this, { list ->
                penghuniAdapter.differ.submitList(list)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}