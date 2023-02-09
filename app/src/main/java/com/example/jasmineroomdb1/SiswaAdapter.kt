package com.example.jasmineroomdb1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeroom1aurelliajasmine.room.Tbsiswa
import kotlinx.android.synthetic.main.activity_siswa_adapter.view.*

class SiswaAdapter (private val siswa: ArrayList<Tbsiswa>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter <SiswaAdapter.SiswaViewHolder>() {

    class SiswaViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaViewHolder {
        return SiswaViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_siswa_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: SiswaViewHolder, position: Int) {
        val Tbsis = siswa[position]
        holder.view.text_title.text = Tbsis.nama
        holder.view.text_title.setOnClickListener {
            listener.onCLick(Tbsis)
        }
        holder.view.icon_edit.setOnClickListener{
            listener.onUpdate(Tbsis)
        }
        holder.view.icon_delete.setOnClickListener{
            listener.onDelete(Tbsis)
        }
    }

    override fun getItemCount() = siswa.size

    fun setData(list: List <Tbsiswa>){
        siswa.clear()
        siswa.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onCLick(tbsiswa: Tbsiswa)
        fun onUpdate(tbsiswa: Tbsiswa)
        fun onDelete(tbsiswa: Tbsiswa)
    }

}