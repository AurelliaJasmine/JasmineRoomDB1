package com.example.jasmineroomdb1.Room

import android.content.Context
import androidx.room.*
import com.example.challengeroom1aurelliajasmine.room.Tbsiswa
import com.example.challengeroom1aurelliajasmine.room.TbsiswaDao

@Database (entities = [Tbsiswa::class], version = 1)
    abstract class dbsmksa : RoomDatabase() {
        abstract fun tbsiswaDao(): TbsiswaDao

        companion object{
            @Volatile private var instance: dbsmksa? = null
            private val LOCK = Any()
            operator fun invoke(context: Context) = instance?: synchronized(LOCK){
                instance?: builDatabase(context).also{
                    instance = it
                }
            }
            private fun builDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                dbsmksa::class.java,
                "smksa.db"
            ).build()
        }
}

 