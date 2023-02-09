package com.example.challengeroom1aurelliajasmine.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tbsiswa (
    @PrimaryKey (autoGenerate = true)
    val nis:Int,
    val nama:String,
    val kelas:String,
    val alamat:String
)