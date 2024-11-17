package com.example.tugasdicoding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Negara(
    val name: String,
    val description: String,
    val photo: Int,
    val longDescription: String,
    val waktuGabung: String,
    val luasWilayah: String
) : Parcelable

