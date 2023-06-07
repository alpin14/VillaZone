package com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyektingkat2.villazone.db.PenghuniDao

@Suppress("UNCHECKED_CAST")
class DaftarPenghuniViewModelFactory(private val dao: PenghuniDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DaftarPenghuniViewModel::class.java)) {
            return DaftarPenghuniViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


