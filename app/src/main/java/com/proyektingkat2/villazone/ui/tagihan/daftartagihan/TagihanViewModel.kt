package com.proyektingkat2.villazone.ui.tagihan.daftartagihan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.repository.PenghuniRepository

class TagihanViewModel(private val repository: PenghuniRepository) : ViewModel() {

    fun getAllTagihan(): LiveData<List<PenghuniEntity>> {
        return repository.getAllPenghuni()
    }
}
