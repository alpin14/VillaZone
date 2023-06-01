package com.proyektingkat2.villazone.model

import java.time.LocalDate

data class Tamu(
    val id: Int,
    val nama: String,
    val nomorKamar: Int,
    val biayaKamar: String,
    val kontakPenghuni: String,
    val tanggalMasuk: LocalDate
)
