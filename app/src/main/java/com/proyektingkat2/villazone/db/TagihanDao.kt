package com.proyektingkat2.villazone.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.proyektingkat2.villazone.model.StatusPembayaran

@Dao
interface TagihanDao {
    @Insert
    suspend fun insertTagihan(tagihanEntity: TagihanEntity)

    @Update
    suspend fun updateTagihan(tagihanEntity: TagihanEntity)

    @Query("DELETE FROM tagihan WHERE penghuniId = :penghuniId")
    suspend fun deleteTagihanByPenghuniId(penghuniId: Int)

    @Query("SELECT * FROM tagihan WHERE penghuniId = :penghuniId")
    suspend fun getTagihanByPenghuniId(penghuniId: Int): TagihanEntity?


    @Query("UPDATE tagihan SET statusPembayaran = :status WHERE penghuniId = :penghuniId")
    suspend fun updateTagihanStatus(penghuniId: Int, status: StatusPembayaran): Int
}