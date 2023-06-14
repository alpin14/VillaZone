package com.proyektingkat2.villazone.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.proyektingkat2.villazone.model.StatusPembayaran

@Dao
interface PenghuniDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPenghuni(penghuni: PenghuniEntity): Long


    @Update
    suspend fun updatePenghuni(penghuniEntity: PenghuniEntity)

    @Delete
    suspend fun deletePenghuni(penghuniEntity: PenghuniEntity)

    @Query("SELECT * FROM penghuni ORDER BY id DESC")
    fun getAllPenghuni(): LiveData<List<PenghuniEntity>>

    @Query("SELECT * FROM penghuni WHERE namaPenghuni LIKE '%' || :query || '%' OR nomorKamar LIKE '%' || :query || '%'")
    fun searchPenghuni(query: String?): LiveData<List<PenghuniEntity>>

    @Query("UPDATE penghuni SET statusPembayaran = :status WHERE id = :penghuniId")
    suspend fun updateTagihanStatus(penghuniId: Int, status: StatusPembayaran): Int
}
