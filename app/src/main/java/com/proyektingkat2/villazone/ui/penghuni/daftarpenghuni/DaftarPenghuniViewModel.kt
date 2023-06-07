package com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyektingkat2.villazone.db.PenghuniDao
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.repository.PenghuniRepository
import kotlinx.coroutines.launch

class DaftarPenghuniViewModel(
    app: Application,
    private val penghuniRepository: PenghuniRepository
    ): AndroidViewModel(app) {

    fun addPenghuni(penghuni: PenghuniEntity) =
        viewModelScope.launch {
            penghuniRepository.insertPenghuni(penghuni)
        }

    fun deletePenghuni(penghuni: PenghuniEntity) =
        viewModelScope.launch {
            penghuniRepository.deletePenghuni(penghuni)
        }

    fun updatePenghuni(penghuni: PenghuniEntity) =
        viewModelScope.launch {
            penghuniRepository.updatePenghuni(penghuni)
        }

    fun getAllPenghuni() = penghuniRepository.getAllPenghuni()

    fun searchPenghuni(query: String?)=
        penghuniRepository.searchPenghuni(query)
}



