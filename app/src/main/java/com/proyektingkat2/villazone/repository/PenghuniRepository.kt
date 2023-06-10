package com.proyektingkat2.villazone.repository

import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.db.PenghuniDao
import com.proyektingkat2.villazone.db.PenghuniEntity
import com.proyektingkat2.villazone.db.TagihanDao
import com.proyektingkat2.villazone.db.TagihanEntity

class PenghuniRepository(db: AppDatabase) {

    private val penghuniDao: PenghuniDao = db.getPenghuniDao()
    private val tagihanDao: TagihanDao = db.getTagihanDao()

    suspend fun insertPenghuniWithTagihan(penghuniEntity: PenghuniEntity) {
        val penghuniId = penghuniDao.insertPenghuni(penghuniEntity)
        val tagihan = TagihanEntity(penghuniId.toInt(), penghuniEntity.namaPenghuni, penghuniEntity.biayaKamar, penghuniEntity.statusPembayaran)
        tagihanDao.insertTagihan(tagihan)
    }

    suspend fun deletePenghuni(penghuniEntity: PenghuniEntity) {
        penghuniDao.deletePenghuni(penghuniEntity)
        tagihanDao.deleteTagihanByPenghuniId(penghuniEntity.id)
    }

    suspend fun updatePenghuni(penghuniEntity: PenghuniEntity) {
        penghuniDao.updatePenghuni(penghuniEntity)
        val tagihan = TagihanEntity(penghuniEntity.id, penghuniEntity.namaPenghuni, penghuniEntity.biayaKamar, penghuniEntity.statusPembayaran)
        tagihanDao.updateTagihan(tagihan)
    }

    fun getAllPenghuni() = penghuniDao.getAllPenghuni()
    fun searchPenghuni(query: String?) = penghuniDao.searchPenghuni(query)
}
