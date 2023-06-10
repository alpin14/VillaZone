package com.proyektingkat2.villazone.ui.tagihan.daftartagihan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyektingkat2.villazone.repository.PenghuniRepository

class TagihanViewModelFactory(private val repository: PenghuniRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TagihanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TagihanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
