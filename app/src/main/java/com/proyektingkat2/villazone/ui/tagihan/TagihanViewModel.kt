package com.proyektingkat2.villazone.ui.tagihan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.model.Tagihan

class TagihanViewModel : ViewModel() {
    private val tagihanList = MutableLiveData<List<Tagihan>>()

    fun getTagihanList(): LiveData<List<Tagihan>> {
        return tagihanList
    }

    fun loadTagihanData() {
        val tagihanData = fetchTagihanDataFromRepository()
        tagihanList.value = tagihanData
    }

    private fun fetchTagihanDataFromRepository(): List<Tagihan> {
        return listOf(
            Tagihan("Asep Gumilang", R.drawable.outline_account_circle_24, "Lunas", "Rp 500.000", 54),
            Tagihan("Sakha Gomblo", R.drawable.outline_account_circle_24, "Belum Lunas", "Rp 1.000.000", 60),
            Tagihan("Rizky Timbo", R.drawable.outline_account_circle_24, "Lunas", "Rp 750.000", 70)
        )
    }
}
