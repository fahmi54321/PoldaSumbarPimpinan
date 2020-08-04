package com.example.poldasumbarpimpinan.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.poldasumbarpimpinan.R
import com.example.poldasumbarpimpinan.adapter.AdapterNaskah
import com.example.poldasumbarpimpinan.model.ResponseNaskah
import com.example.poldasumbarpimpinan.model.ResultItemNaskah
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import com.example.poldasumbarpimpinan.presenter.naskah.NaskahPresenter
import com.example.poldasumbarpimpinan.presenter.naskah.NaskahView
import kotlinx.android.synthetic.main.activity_naskah_kerma.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GetNaskahKermaActivity : AppCompatActivity(), NaskahView {
    var presenter:NaskahPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naskah_kerma)

        presenter = NaskahPresenter(this)

        buttonKesatuan.setOnClickListener {
            showProgress()
            presenter?.getNaskah()
        }

        immgSearch.setOnClickListener {
            showDialogSearch()
        }
    }

    override fun onSuccess(message: String, result: List<ResultItemNaskah?>?) {
        toast("Berhasil")
        getNaskah.adapter = AdapterNaskah(result,this)
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideProgress() {
        progressBarNaskah.visibility = View.GONE
    }

    override fun hideLoading() {
        buttonKesatuan.setBackgroundResource(R.drawable.bg_input_view)
        buttonKesatuan.text = "Tampilkan Data"
        buttonKesatuan.isEnabled = true
    }

    override fun dataKosong() {
        toast("Data Kosong")
    }


    fun toast(message: String)
    {
        Toast.makeText(this,message, Toast.LENGTH_LONG)
    }
    fun showProgress()
    {
        buttonKesatuan.setBackgroundResource(R.drawable.bg_input_view_loading)
        progressBarNaskah.visibility = View.VISIBLE
        buttonKesatuan.text = "Loading..."
        buttonKesatuan.isEnabled = false
    }

    private fun showDialogSearch() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_item_search_naskah)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val close = dialog.findViewById<ImageView>(R.id.imgClose)
        val cari = dialog.findViewById<Button>(R.id.buttonCari)
        val kesatuan = dialog.findViewById<EditText>(R.id.edtKesatuan)
        val tentang = dialog.findViewById<EditText>(R.id.edtTentang)
        val searchNaskah = dialog.findViewById<RecyclerView>(R.id.getNaskahSearch)
        val progressBarNaskahSearch = dialog.findViewById<ProgressBar>(R.id.progressBarSearch)
        val buttoCari = dialog.findViewById<Button>(R.id.buttonCari)

        close.setOnClickListener {
            dialog.dismiss()
        }

        cari.setOnClickListener {

            buttoCari.setBackgroundResource(R.drawable.bg_input_view_loading)
            progressBarNaskahSearch.visibility = View.VISIBLE
            cari.text = "Loading..."
            cari.isEnabled = false

            val Txtkesatuan = kesatuan.text.toString()
            val TxtTentang = tentang.text.toString()

            ConfigNetwork.getRetrofit().searchNaskah(Txtkesatuan, TxtTentang).enqueue(object :
                Callback<ResponseNaskah> {
                override fun onFailure(call: Call<ResponseNaskah>, t: Throwable) {
                    Log.d("response gagal",t.localizedMessage)
                    Toast.makeText(dialog.context,"Gagal",Toast.LENGTH_LONG).show()

                    buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                    progressBarNaskahSearch.visibility = View.GONE
                    cari.text = "Cari"
                    cari.isEnabled = true
                }

                override fun onResponse(call: Call<ResponseNaskah>, response: Response<ResponseNaskah>) {
                    if (response.isSuccessful)
                    {
                        Log.d("response Berhasil",response.message())
                        val data =response.body()?.result
                        if (data?.size?:0>0)
                        {
                            Toast.makeText(dialog.context,"Data Ditemukan",Toast.LENGTH_LONG).show()
                            searchNaskah.adapter = AdapterNaskah(data,this@GetNaskahKermaActivity)
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarNaskahSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                        else
                        {
                            Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarNaskahSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                    }
                    else
                    {
                        Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                        buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                        progressBarNaskahSearch.visibility = View.GONE
                        cari.text = "Cari"
                        cari.isEnabled = true
                    }
                }

            })
        }

        dialog.show()
    }
}