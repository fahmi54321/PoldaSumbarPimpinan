package com.example.poldasumbarpimpinan.model

import com.google.gson.annotations.SerializedName

data class ResponseNaskah(

	@field:SerializedName("result")
	val result: List<ResultItemNaskah?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultItemNaskah(

	@field:SerializedName("para_pihak")
	val paraPihak: String? = null,

	@field:SerializedName("tentang")
	val tentang: String? = null,

	@field:SerializedName("nomor_naskah")
	val nomorNaskah: String? = null,

	@field:SerializedName("tgl_naskah")
	val tglNaskah: String? = null,

	@field:SerializedName("masa_berlaku")
	val masaBerlaku: String? = null,

	@field:SerializedName("kesatuan")
	val kesatuan: String? = null,

	@field:SerializedName("id_naskah")
	val idNaskah: String? = null,

	@field:SerializedName("upload_document")
	val uploadDocument: String? = null

	)
