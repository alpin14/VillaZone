package com.proyektingkat2.villazone.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PenghuniDao {
    @Query("SELECT * FROM penghuni")
    fun getAllPenghuni(): LiveData<List<PenghuniEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPenghuni(penghuniEntity: PenghuniEntity)
}
