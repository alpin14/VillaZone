package com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyektingkat2.villazone.repository.PenghuniRepository

@Suppress("UNCHECKED_CAST")
class DaftarPenghuniViewModelFactory(
    val app: Application,
    private val penghuniRepository: PenghuniRepository
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DaftarPenghuniViewModel(app, penghuniRepository) as T
    }
}


