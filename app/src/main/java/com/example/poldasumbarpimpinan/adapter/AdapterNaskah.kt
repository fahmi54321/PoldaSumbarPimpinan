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
import com.example.poldasumbarpimpinan.model.ResultItemNaskah
import kotlinx.android.synthetic.main.item_get_naskah.view.*
import kotlinx.android.synthetic.main.item_get_naskah.view.buttonDetails
import kotlinx.android.synthetic.main.item_get_naskah.view.kesatuan
import java.util.*

class AdapterNaskah(
    var data:List<ResultItemNaskah?>?,
    var context: Context):RecyclerView.Adapter<AdapterNaskah.NaskahViewHolder>() {
    class NaskahViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var kesatuan = itemView.kesatuan
        var nomor_naskah = itemView.nomor_naskah
        var tgl_naskah = itemView.tgl_naskah
        var para_pihak = itemView.para_pihak
        var tentang = itemView.tentang
        var masa_berlaku = itemView.masa_berlaku
        var id_naskah = itemView.id_naskah
        var upload_document = itemView.upload_document
        var buttonDetails = itemView.buttonDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaskahViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_get_naskah,parent,false)
        var holder =
            NaskahViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: NaskahViewHolder, position: Int) {
        holder.kesatuan.text = data?.get(position)?.kesatuan
        holder.nomor_naskah.text = data?.get(position)?.nomorNaskah
        holder.tgl_naskah.text = data?.get(position)?.tglNaskah
        holder.para_pihak.text = data?.get(position)?.paraPihak
        holder.tentang.text = data?.get(position)?.tentang
        holder.masa_berlaku.text = data?.get(position)?.masaBerlaku
        holder.id_naskah.text = data?.get(position)?.idNaskah
        holder.upload_document.text = data?.get(position)?.uploadDocument

        holder.buttonDetails.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_item_details_naskah)

            Objects.requireNonNull(dialog.window)
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            var imgClose = dialog.findViewById<ImageView>(R.id.imgClose)
            var kesatuan = dialog.findViewById<TextView>(R.id.kesatuan)
            var nomorNaskah = dialog.findViewById<TextView>(R.id.nomor_naskah)
            var tglNaskah = dialog.findViewById<TextView>(R.id.tgl_naskah)
            var paraPihak = dialog.findViewById<TextView>(R.id.para_pihak)
            var tentang = dialog.findViewById<TextView>(R.id.tentang)
            var masaBerlaku = dialog.findViewById<TextView>(R.id.masa_berlaku)
            var uploadDocument = dialog.findViewById<TextView>(R.id.upload_document)

            imgClose.setOnClickListener {
                dialog.dismiss()
            }

            kesatuan.text = data?.get(position)?.kesatuan
            nomorNaskah.text = data?.get(position)?.nomorNaskah
            tglNaskah.text = data?.get(position)?.tglNaskah
            paraPihak.text = data?.get(position)?.paraPihak
            tentang.text = data?.get(position)?.tentang
            masaBerlaku.text = data?.get(position)?.masaBerlaku
            uploadDocument.text = data?.get(position)?.uploadDocument

            dialog.show()
        }
    }
}