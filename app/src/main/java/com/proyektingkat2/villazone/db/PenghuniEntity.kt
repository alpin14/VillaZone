package com.proyektingkat2.villazone.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "penghuni")
@Parcelize
data class PenghuniEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaPenghuni: String,
    val nomorHp: String,
    val nomorKamar: Int,
    val biayaKamar: Double,
    val tanggalMasuk: String
) : Parcelable
