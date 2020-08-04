package com.example.poldasumbarpimpinan.model

import com.google.gson.annotations.SerializedName

data class ResponsePeraturan(

	@field:SerializedName("result")
	val result: List<ResultItemPeraturan?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultItemPeraturan(

	@field:SerializedName("tgl_pengesahan")
	val tglPengesahan: String? = null,

	@field:SerializedName("nomor_peraturan")
	val nomorPeraturan: String? = null,

	@field:SerializedName("tentang")
	val tentang: String? = null,

	@field:SerializedName("id_peraturan")
	val idPeraturan: String? = null,

	@field:SerializedName("nama_pejabat")
	val namaPejabat: String? = null,

	@field:SerializedName("kesatuan")
	val kesatuan: String? = null,

	@field:SerializedName("pilih_peraturan")
	val pilihPeraturan: String? = null,

	@field:SerializedName("tgl_penetapan")
	val tglPenetapan: String? = null,

	@field:SerializedName("no_register")
	val noRegister: String? = null,

	@field:SerializedName("upload_peraturan")
	val uploadPeraturan: String? = null
)
