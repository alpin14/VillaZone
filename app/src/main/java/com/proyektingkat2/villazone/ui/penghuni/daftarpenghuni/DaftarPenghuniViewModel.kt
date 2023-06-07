package com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyektingkat2.villazone.db.PenghuniDao
import com.proyektingkat2.villazone.db.PenghuniEntity
import kotlinx.coroutines.launch

class DaftarPenghuniViewModel(private val db: PenghuniDao) : ViewModel() {

    val allPenghuniLiveData: LiveData<List<PenghuniEntity>> = db.getAllPenghuni()

    fun insertPenghuni(penghuni: PenghuniEntity) {
        viewModelScope.launch {
            db.insertPenghuni(penghuni)
        }
    }
}


