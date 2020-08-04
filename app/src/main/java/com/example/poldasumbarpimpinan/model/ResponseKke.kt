package com.example.poldasumbarpimpinan.model

import com.google.gson.annotations.SerializedName

data class ResponseKke(

	@field:SerializedName("result")
	val result: List<ResultItemKke?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultItemKke(

	@field:SerializedName("tgl_sidang")
	val tglSidang: String? = null,

	@field:SerializedName("no_laporan")
	val noLaporan: String? = null,

	@field:SerializedName("nama_pelapor")
	val namaPelapor: String? = null,

	@field:SerializedName("tgl_laporan")
	val tglLaporan: String? = null,

	@field:SerializedName("no_sidang")
	val noSidang: String? = null,

	@field:SerializedName("jabatan")
	val jabatan: String? = null,

	@field:SerializedName("kesatuan")
	val kesatuan: String? = null,

	@field:SerializedName("nrp")
	val nrp: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("nama_pelaku")
	val namaPelaku: String? = null,

	@field:SerializedName("pangkat_nrp")
	val pangkatNrp: String? = null,

	@field:SerializedName("id_kke")
	val idKke: String? = null,

	@field:SerializedName("nomor_psh")
	val nomorPsh: String? = null,

	@field:SerializedName("isi_putusan")
	val isiPutusan: String? = null,

	@field:SerializedName("pasal_dilanggar")
	val pasalDilanggar: String? = null,

	@field:SerializedName("isi_banding")
	val isiBanding: String? = null,

	@field:SerializedName("photo_vonis")
	val photoVonis: String? = null,

	@field:SerializedName("photo_putusan")
	val photoPutusan: String? = null,

	@field:SerializedName("photo_surat")
	val photoSurat: String? = null
)
