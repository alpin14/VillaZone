package com.proyektingkat2.villazone.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.proyektingkat2.villazone.model.StatusPembayaran

@Entity(
    tableName = "tagihan",
    foreignKeys = [ForeignKey(
        entity = PenghuniEntity::class,
        parentColumns = ["id"],
        childColumns = ["penghuniId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TagihanEntity(
    @PrimaryKey(autoGenerate = true)
    val penghuniId: Int = 0,
    val namaPenghuni: String,
    val biayaKamar: String,
    val statusPembayaran: StatusPembayaran
)

