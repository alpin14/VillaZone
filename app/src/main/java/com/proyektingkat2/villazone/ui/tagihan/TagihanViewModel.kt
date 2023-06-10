package com.proyektingkat2.villazone.ui.tagihan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.repository.PenghuniRepository
import kotlinx.coroutines.launch

class TagihanViewModel(private val repository: PenghuniRepository) : ViewModel() {

    private val _penghuniList = MutableLiveData<List<PenghuniEntity>>()
    val penghuniList: LiveData<List<PenghuniEntity>> = _penghuniList

    init {
        getPenghuniList()
    }

    private fun getPenghuniList() {
        viewModelScope.launch {
            val penghuniList = repository.getAllPenghuni()
            _penghuniList.value = penghuniList.
        }
    }
}
