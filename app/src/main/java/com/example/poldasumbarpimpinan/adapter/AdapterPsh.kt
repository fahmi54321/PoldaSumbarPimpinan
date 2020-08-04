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
import com.example.poldasumbarpimpinan.model.ResultItemPsh
import kotlinx.android.synthetic.main.item_get_psh.view.*
import kotlinx.android.synthetic.main.item_get_psh.view.buttonDetails
import kotlinx.android.synthetic.main.item_get_psh.view.kesatuan
import kotlinx.android.synthetic.main.item_get_psh.view.nrp
import kotlinx.android.synthetic.main.item_get_psh.view.pangkat
import kotlinx.android.synthetic.main.item_get_psh.view.pasal_dilanggar
import java.util.*

class AdapterPsh(
    var data:List<ResultItemPsh?>?,
    var context: Context):RecyclerView.Adapter<AdapterPsh.PshViewHolder>() {
    class PshViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var perkara = itemView.perkara
        var nomor_psh = itemView.nomor_psh
        var tanggal = itemView.tanggal
        var nama_terduga = itemView.nama_terduga_pelanggar
        var pangkat = itemView.pangkat
        var nrp = itemView.nrp
        var kesatuan = itemView.kesatuan
        var pasal_dilanggar = itemView.pasal_dilanggar
        var id_psh = itemView.id_psh
        var buttonDetails = itemView.buttonDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PshViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_get_psh,parent,false)
        var holder =
            PshViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: PshViewHolder, position: Int) {
        holder.perkara.text = data?.get(position)?.perkara
        holder.nomor_psh.text = data?.get(position)?.nomorPsh
        holder.tanggal.text = data?.get(position)?.tanggal
        holder.nama_terduga.text = data?.get(position)?.nama
        holder.pangkat.text = data?.get(position)?.pangkatNrp
        holder.nrp.text = data?.get(position)?.nrp
        holder.kesatuan.text = data?.get(position)?.kesatuan
        holder.pasal_dilanggar.text = data?.get(position)?.pasalDilanggar
        holder.id_psh.text = data?.get(position)?.idPsh

        holder.buttonDetails.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_item_details_psh)

            Objects.requireNonNull(dialog.window)
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            var imgClose = dialog.findViewById<ImageView>(R.id.imgClose)
            var perkara = dialog.findViewById<TextView>(R.id.perkara)
            var nomor_psh = dialog.findViewById<TextView>(R.id.nomor_psh)
            var tgl = dialog.findViewById<TextView>(R.id.tanggal)
            var nama_terduga = dialog.findViewById<TextView>(R.id.nama_terduga_pelanggar)
            var pangkat = dialog.findViewById<TextView>(R.id.pangkat)
            var nrp = dialog.findViewById<TextView>(R.id.nrp)
            var kesatuan = dialog.findViewById<TextView>(R.id.kesatuan)
            var pasalDilanggar = dialog.findViewById<TextView>(R.id.pasal_dilanggar)
            var photoVonis = dialog.findViewById<TextView>(R.id.photo_vonis)

            imgClose.setOnClickListener {
                dialog.dismiss()
            }

            perkara.text = data?.get(position)?.perkara
            nomor_psh.text = data?.get(position)?.nomorPsh
            tgl.text = data?.get(position)?.tanggal
            nama_terduga.text = data?.get(position)?.nama
            pangkat.text = data?.get(position)?.pangkatNrp
            nrp.text = data?.get(position)?.nrp
            kesatuan.text = data?.get(position)?.kesatuan
            pasalDilanggar.text = data?.get(position)?.pasalDilanggar
            photoVonis.text = data?.get(position)?.photoVonis

            dialog.show()
        }
    }
}