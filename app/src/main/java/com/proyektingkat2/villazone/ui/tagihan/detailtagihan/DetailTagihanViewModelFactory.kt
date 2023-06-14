package com.proyektingkat2.villazone.ui.tagihan.detailtagihan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyektingkat2.villazone.repository.PenghuniRepository

class DetailTagihanViewModelFactory(private val repository: PenghuniRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailTagihanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailTagihanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}