package com.proyektingkat2.villazone.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PenghuniDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPenghuni(penghuniEntity: PenghuniEntity)

    @Update
    suspend fun updatePenghuni(penghuniEntity: PenghuniEntity)

    @Delete
    suspend fun deletePenghuni(penghuniEntity: PenghuniEntity)

    @Query("SELECT * FROM penghuni ORDER BY id DESC")
    fun getAllPenghuni(): LiveData<List<PenghuniEntity>>

    @Query("SELECT * FROM penghuni WHERE namaPenghuni LIKE '%' || :query || '%' OR nomorKamar LIKE '%' || :query || '%'")
    fun searchPenghuni(query: String?): LiveData<List<PenghuniEntity>>
}
