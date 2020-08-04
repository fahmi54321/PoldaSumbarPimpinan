package com.example.poldasumbarpimpinan.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItemLogin?>? = null
)

data class ResultsItemLogin(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("pangkat")
	val pangkat: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("kesatuan")
	val kesatuan: String? = null,

	@field:SerializedName("nrp")
	val nrp: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
