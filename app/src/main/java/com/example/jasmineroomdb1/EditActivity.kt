package com.example.jasmineroomdb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeroom1aurelliajasmine.room.Tbsiswa
import com.example.jasmineroomdb1.Room.Constant
import com.example.jasmineroomdb1.Room.dbsmksa
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { dbsmksa(this) }
    private var tbsisnis : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolPerintah()
        setupView()
        tbsisnis = intent.getIntExtra("intent_nis", tbsisnis)
        Toast.makeText(this, tbsisnis.toString(), Toast.LENGTH_SHORT).show()

    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnSave.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                etNis.visibility = View.GONE
                tampilsiswa()
            }
            Constant.TYPE_UPDATE -> {
                btnSave.visibility = View.GONE
                etNis.visibility = View.GONE
                tampilsiswa()
            }
        }
    }

    fun tombolPerintah(){
        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbsiswaDao().addTbsiswa(
                    Tbsiswa(
                        etNis.text.toString().toInt(),
                            etNama.text.toString(),
                            etKelas.text.toString(),
                            etAlamat.text.toString())
                )
                finish()
            }
        }

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbsiswaDao().updateTbsiswa(
                    Tbsiswa(tbsisnis,
                        etNama.text.toString(),
                        etKelas.text.toString(),
                        etAlamat.text.toString()
                    )
                )
                finish()
            }
        }
    }

    fun tampilsiswa(){
        tbsisnis = intent.getIntExtra("intent_nis", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val siswa = db.tbsiswaDao().tampilsiswa(tbsisnis)[0]
            val datanis : String = siswa.nis.toString()
            etNis.setText(datanis)
            etNama.setText(siswa.nama)
            etKelas.setText(siswa.kelas)
            etAlamat.setText(siswa.alamat)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
