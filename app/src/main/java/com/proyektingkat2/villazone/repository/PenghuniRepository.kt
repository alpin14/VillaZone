package com.proyektingkat2.villazone.repository

import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.db.PenghuniEntity

class PenghuniRepository(private val db: AppDatabase) {

    suspend fun insertPenghuni(penghuniEntity: PenghuniEntity) =
        db.getPenghuniDao().insertPenghuni(penghuniEntity)

    suspend fun deletePenghuni(penghuniEntity: PenghuniEntity) =
        db.getPenghuniDao().deletePenghuni(penghuniEntity)

    suspend fun updatePenghuni(penghuniEntity: PenghuniEntity) =
        db.getPenghuniDao().updatePenghuni(penghuniEntity)

    fun getAllPenghuni() = db.getPenghuniDao().getAllPenghuni()
    fun searchPenghuni(query: String?) = db.getPenghuniDao().searchPenghuni(query)
}