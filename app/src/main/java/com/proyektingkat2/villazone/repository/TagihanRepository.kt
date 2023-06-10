package com.proyektingkat2.villazone.repository

import androidx.lifecycle.LiveData
import com.proyektingkat2.villazone.db.PenghuniDao
import com.proyektingkat2.villazone.db.PenghuniEntity

class TagihanRepository(private val penghuniDao: PenghuniDao) {

    fun tagihanList(): LiveData<List<PenghuniEntity>> {
        return penghuniDao.getAllPenghuni()
    }
}
