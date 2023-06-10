package com.proyektingkat2.villazone.ui.tagihan

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.proyektingkat2.villazone.databinding.FragmentDaftarPenghuniBinding

class TagihanAdapter: Fragment(){
    private val _binding : FragmentDaftarPenghuniBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TagihanViewModel by viewModels()
    private lateinit var adapter: TagihanAdapter



}