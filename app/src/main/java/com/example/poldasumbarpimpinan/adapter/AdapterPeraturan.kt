package com.example.poldasumbarpimpinan.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poldasumbarpimpinan.R
import com.example.poldasumbarpimpinan.model.ResultItemPeraturan
import kotlinx.android.synthetic.main.item_get_peraturan.view.*
import java.util.*

class AdapterPeraturan(
    var data:List<ResultItemPeraturan?>?,
    var context: Context):RecyclerView.Adapter<AdapterPeraturan.PeraturanViewHolder>() {
    class PeraturanViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var pilih_peraturan = itemView.pilih_peraturan
        var nomor_peraturan = itemView.nomor_peraturan
        var tgl_penetapan = itemView.tgl_penetapan
        var tgl_pengesahan = itemView.tgl_pengesahan
        var nama_pejabat = itemView.nama_pejabat_tanda_tangan
        var no_register = itemView.no_register
        var tentang = itemView.tentang
        var id_peraturan = itemView.id_peraturan
        var buttonDetails= itemView.buttonDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeraturanViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_get_peraturan,parent,false)
        var holder =
            PeraturanViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: PeraturanViewHolder, position: Int) {
        holder.pilih_peraturan.text = data?.get(position)?.pilihPeraturan
        holder.nomor_peraturan.text = data?.get(position)?.nomorPeraturan
        holder.tgl_penetapan.text = data?.get(position)?.tglPenetapan
        holder.tgl_pengesahan.text = data?.get(position)?.tglPengesahan
        holder.nama_pejabat.text = data?.get(position)?.namaPejabat
        holder.no_register.text = data?.get(position)?.noRegister
        holder.tentang.text = data?.get(position)?.tentang
        holder.id_peraturan.text = data?.get(position)?.idPeraturan

        holder.buttonDetails.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_item_details_peraturan)

            Objects.requireNonNull(dialog.window)
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            var pilih_peraturan = dialog.findViewById<TextView>(R.id.pilih_peraturan)
            var nomor_peraturan = dialog.findViewById<TextView>(R.id.nomor_peraturan)
            var tgl_penetapan = dialog.findViewById<TextView>(R.id.tgl_penetapan)
            var tgl_pengesahan = dialog.findViewById<TextView>(R.id.tgl_pengesahan)
            var nama_pejabat = dialog.findViewById<TextView>(R.id.nama_pejabat_tanda_tangan)
            var no_register = dialog.findViewById<TextView>(R.id.no_register)
            var tentang = dialog.findViewById<TextView>(R.id.tentang)
            var upload_peraturan = dialog.findViewById<TextView>(R.id.upload_document)
            var imgClose = dialog.findViewById<ImageView>(R.id.imgClose)

            pilih_peraturan.text = data?.get(position)?.pilihPeraturan
            nomor_peraturan.text = data?.get(position)?.nomorPeraturan
            tgl_penetapan.text = data?.get(position)?.tglPenetapan
            tgl_pengesahan.text = data?.get(position)?.tglPengesahan
            nama_pejabat.text = data?.get(position)?.namaPejabat
            no_register.text = data?.get(position)?.noRegister
            tentang.text = data?.get(position)?.tentang
            upload_peraturan.text = data?.get(position)?.uploadPeraturan

            imgClose.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }
}