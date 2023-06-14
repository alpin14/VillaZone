package com.proyektingkat2.villazone.ui.tagihan.detailtagihan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyektingkat2.villazone.model.StatusPembayaran
import com.proyektingkat2.villazone.repository.PenghuniRepository
import kotlinx.coroutines.launch

class DetailTagihanViewModel(private val repository: PenghuniRepository) : ViewModel() {
    fun updateTagihanStatus(tagihanId: Int, status: StatusPembayaran) {
        viewModelScope.launch {
            repository.updateTagihanStatus(tagihanId, status)
            Log.d("update status", "update selesai")
        }
    }
}