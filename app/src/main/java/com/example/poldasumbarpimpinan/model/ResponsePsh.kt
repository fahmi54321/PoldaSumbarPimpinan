package com.example.poldasumbarpimpinan.model

import com.google.gson.annotations.SerializedName

data class ResponsePsh(

	@field:SerializedName("result")
	val result: List<ResultItemPsh?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultItemPsh(

	@field:SerializedName("pangkat_nrp")
	val pangkatNrp: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("perkara")
	val perkara: String? = null,

	@field:SerializedName("id_psh")
	val idPsh: String? = null,

	@field:SerializedName("nomor_psh")
	val nomorPsh: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("kesatuan")
	val kesatuan: String? = null,

	@field:SerializedName("nrp")
	val nrp: String? = null,

	@field:SerializedName("pasal_dilanggar")
	val pasalDilanggar: String? = null,

	@field:SerializedName("photo_vonis")
	val photoVonis: String? = null
)
