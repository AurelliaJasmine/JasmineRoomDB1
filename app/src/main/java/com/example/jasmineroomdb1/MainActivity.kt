package com.example.jasmineroomdb1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom1aurelliajasmine.room.Tbsiswa
import com.example.jasmineroomdb1.Room.Constant
import com.example.jasmineroomdb1.Room.dbsmksa
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { dbsmksa(this) }
    lateinit var siswaAdapter : SiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRV()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val siswa = db.tbsiswaDao().getTbsiswa()
            Log.d("MainActivity", "dbResponse: $siswa")
            withContext(Dispatchers.Main){
                siswaAdapter.setData(siswa)
            }
        }
    }

    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0, Constant.TYPE_CREATE)
            startActivity(Intent(this,EditActivity::class.java))
        }
    }

    private fun intentEdit(tbsisnis: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_nis",tbsisnis)
                .putExtra("intent_type",intentType)
        )
    }

    fun setupRV(){
        siswaAdapter = SiswaAdapter(arrayListOf(), object : SiswaAdapter.OnAdapterListener{
            override fun onCLick(tbsiswa: Tbsiswa) {
                intentEdit(tbsiswa.nis, Constant.TYPE_READ)
            }

            override fun onUpdate(tbsiswa: Tbsiswa) {
                intentEdit(tbsiswa.nis, Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbsiswa: Tbsiswa) {
                hapussiswa(tbsiswa)
            }
        })


        // id recyclerview
        list_RV.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = siswaAdapter
        }
    }

    private fun hapussiswa(tbsiswa: Tbsiswa){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin Hapus ${tbsiswa.nama}?")
            setNegativeButton("Batal") {dialogInterface, i ->
                dialogInterface.dismiss()
                }
            setPositiveButton("Hapus") {dialogInterface, i ->
               CoroutineScope(Dispatchers.IO).launch {
                   db.tbsiswaDao().deleteTbsiswa(tbsiswa)
                   dialogInterface.dismiss()
                   loadData()
               }
            }
        }
        alertDialog.show()
    }

}
