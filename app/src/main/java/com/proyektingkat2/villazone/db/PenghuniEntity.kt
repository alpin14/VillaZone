package com.proyektingkat2.villazone.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penghuni")
data class PenghuniEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nama_penghuni") val namaPenghuni: String,
    @ColumnInfo(name = "nomor_hp") val nomorHp: String,
    @ColumnInfo(name = "nomor_kamar") val nomorKamar: Int,
    @ColumnInfo(name = "biaya_kamar") val biayaKamar: Int,
    @ColumnInfo(name = "tanggal_masuk") val tanggalMasuk: String
)
