package com.proyektingkat2.villazone.model

data class Penghuni(
    val namaPenghuni: String,
    val nomorHp: String,
    val nomorKamar: Int,
    val biayaKamar: Int,
    val tanggalMasuk: String,
    val tagihan: List<Tagihan>,
    val statusPembayaran: StatusPembayaran
)
