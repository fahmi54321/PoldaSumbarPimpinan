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
import com.example.poldasumbarpimpinan.model.ResultItemKke
import kotlinx.android.synthetic.main.item_get_kke.view.*
import kotlinx.android.synthetic.main.item_get_kke.view.buttonDetails
import kotlinx.android.synthetic.main.item_get_kke.view.jabatan
import kotlinx.android.synthetic.main.item_get_kke.view.nama_pelaku
import kotlinx.android.synthetic.main.item_get_kke.view.nama_pelapor
import java.util.*

class AdapterKke(
    var data:List<ResultItemKke?>?,
    var context: Context):RecyclerView.Adapter<AdapterKke.KkeViewHolder>() {
    class KkeViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var nama_pelaku = itemView.nama_pelaku
        var pangkat = itemView.pangkatKke
        var nrp = itemView.nrpKke
        var jabatan = itemView.jabatan
        var kesatuan = itemView.kesatuanKke
        var nama_pelapor = itemView.nama_pelapor
        var pasal_dilanggar = itemView.pasal_dilanggar_kke
        var id_kke = itemView.id_kke
        var buttonDetails = itemView.buttonDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KkeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_get_kke,parent,false)
        var holder =
            KkeViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: KkeViewHolder, position: Int) {
        holder.nama_pelaku.text = data?.get(position)?.namaPelaku
        holder.pangkat.text = data?.get(position)?.pangkatNrp
        holder.nrp.text = data?.get(position)?.nrp
        holder.jabatan.text = data?.get(position)?.jabatan
        holder.kesatuan.text = data?.get(position)?.kesatuan
        holder.nama_pelapor.text = data?.get(position)?.namaPelapor
        holder.pasal_dilanggar.text = data?.get(position)?.pasalDilanggar
        holder.id_kke.text = data?.get(position)?.idKke

        holder.buttonDetails.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_item_details_disiplin)

            Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            var imgClose = dialog.findViewById<ImageView>(R.id.imgClose)
            var namaPelaku = dialog.findViewById<TextView>(R.id.nama_pelaku)
            var pangkat = dialog.findViewById<TextView>(R.id.pangkat)
            var nrp = dialog.findViewById<TextView>(R.id.nrp)
            var jabatan = dialog.findViewById<TextView>(R.id.jabatan)
            var kesatuan = dialog.findViewById<TextView>(R.id.kesatuan)
            var namaPelapor = dialog.findViewById<TextView>(R.id.nama_pelapor)
            var alamat = dialog.findViewById<TextView>(R.id.alamat)
            var pasalDilanggar = dialog.findViewById<TextView>(R.id.pasal_dilanggar)
            var noLaporan = dialog.findViewById<TextView>(R.id.no_laporan)
            var tglLaporan = dialog.findViewById<TextView>(R.id.tgl_laporan)
            var noBerkasLaporan = dialog.findViewById<TextView>(R.id.no_berkas_laporan)
            var nomorPsh = dialog.findViewById<TextView>(R.id.nomor_psh)
            var tglSidang = dialog.findViewById<TextView>(R.id.tgl_sidang)
            var isiPutusan = dialog.findViewById<TextView>(R.id.isi_putusan)
            var isiBanding = dialog.findViewById<TextView>(R.id.isi_banding)
            var photoVonis = dialog.findViewById<TextView>(R.id.photo_vonis)
            var photoSurat = dialog.findViewById<TextView>(R.id.photo_surat)
            var photoPutusan = dialog.findViewById<TextView>(R.id.photo_putusan)

            imgClose.setOnClickListener {
                dialog.dismiss()
            }

            namaPelaku.text = data?.get(position)?.namaPelaku
            pangkat.text = data?.get(position)?.pangkatNrp
            nrp.text = data?.get(position)?.nrp
            jabatan.text = data?.get(position)?.jabatan
            kesatuan.text = data?.get(position)?.kesatuan
            namaPelapor.text = data?.get(position)?.namaPelapor
            alamat.text = data?.get(position)?.alamat
            pasalDilanggar.text = data?.get(position)?.pasalDilanggar
            noLaporan.text = data?.get(position)?.noLaporan
            tglLaporan.text = data?.get(position)?.tglLaporan
            noBerkasLaporan.text = data?.get(position)?.noSidang
            nomorPsh.text = data?.get(position)?.nomorPsh
            tglSidang.text = data?.get(position)?.tglSidang
            isiPutusan.text = data?.get(position)?.isiPutusan
            isiBanding.text = data?.get(position)?.isiBanding
            photoVonis.text = data?.get(position)?.photoVonis
            photoSurat.text = data?.get(position)?.photoSurat
            photoPutusan.text = data?.get(position)?.photoPutusan

            dialog.show()
        }
    }
}