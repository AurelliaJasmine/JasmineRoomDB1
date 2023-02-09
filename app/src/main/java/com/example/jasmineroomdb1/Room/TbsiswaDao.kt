package com.example.challengeroom1aurelliajasmine.room

import androidx.room.*

@Dao
interface TbsiswaDao{
    @Insert
     fun addTbsiswa(tbsiswa: Tbsiswa)

    @Update
     fun updateTbsiswa(tbsiswa: Tbsiswa)

    @Delete
     fun deleteTbsiswa(tbsiswa: Tbsiswa)

    @Query("SELECT*FROM Tbsiswa")
     fun getTbsiswa(): List<Tbsiswa>

    @Query("SELECT*FROM Tbsiswa WHERE nis=:tbsis_nis")
    fun tampilsiswa(tbsis_nis: Int): List<Tbsiswa>
}