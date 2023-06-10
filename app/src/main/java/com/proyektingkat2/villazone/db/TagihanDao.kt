package com.proyektingkat2.villazone.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

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

    @Query("SELECT * FROM tagihan")
    suspend fun getAllTagihan(): List<TagihanEntity>
}
