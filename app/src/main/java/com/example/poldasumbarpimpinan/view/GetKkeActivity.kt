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
import com.example.poldasumbarpimpinan.adapter.AdapterKke
import com.example.poldasumbarpimpinan.model.ResponseKke
import com.example.poldasumbarpimpinan.model.ResultItemKke
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import com.example.poldasumbarpimpinan.presenter.kke.KkePresenter
import com.example.poldasumbarpimpinan.presenter.kke.KkeView
import kotlinx.android.synthetic.main.activity_get_kke.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GetKkeActivity : AppCompatActivity(), KkeView {

    var pressenter:KkePresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_kke)

        pressenter = KkePresenter(this)
        buttonTampil.setOnClickListener {
            showProgress()
            pressenter?.getKke()
        }

        immgSearch.setOnClickListener {
            showDialogSearch()
        }
    }

    override fun onSuccess(message: String, result: List<ResultItemKke?>?) {
        toast("Berhasil")
        getKke.adapter = AdapterKke(result,this)
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideProgress() {
        progressBarKke.visibility = View.GONE
    }

    override fun hideLoading() {
        buttonTampil.setBackgroundResource(R.drawable.bg_input_view)
        buttonTampil.text = "Tampilkan Data"
        buttonTampil.isEnabled = true
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
        buttonTampil.setBackgroundResource(R.drawable.bg_input_view_loading)
        progressBarKke.visibility = View.VISIBLE
        buttonTampil.text = "Loading..."
        buttonTampil.isEnabled = false
    }

    private fun showDialogSearch() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_item_search_kke)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val close = dialog.findViewById<ImageView>(R.id.imgClose)
        val cari = dialog.findViewById<Button>(R.id.buttonCari)
        val kesatuan = dialog.findViewById<EditText>(R.id.edtKesatuan)
        val nama_pelaku = dialog.findViewById<EditText>(R.id.edtNamaPelaku)
        val searcKke = dialog.findViewById<RecyclerView>(R.id.getkkeSearch)
        val progressBarKkeSearch = dialog.findViewById<ProgressBar>(R.id.progressBarSearch)
        val buttoCari = dialog.findViewById<Button>(R.id.buttonCari)

        close.setOnClickListener {
            dialog.dismiss()
        }

        cari.setOnClickListener {

            buttoCari.setBackgroundResource(R.drawable.bg_input_view_loading)
            progressBarKkeSearch.visibility = View.VISIBLE
            cari.text = "Loading..."
            cari.isEnabled = false

            val Txtkesatuan = kesatuan.text.toString()
            val TxtNama_Pelaku = nama_pelaku.text.toString()

            ConfigNetwork.getRetrofit().searchKke(Txtkesatuan, TxtNama_Pelaku).enqueue(object :
                Callback<ResponseKke> {
                override fun onFailure(call: Call<ResponseKke>, t: Throwable) {
                    Log.d("response gagal",t.localizedMessage)
                    Toast.makeText(dialog.context,"Gagal",Toast.LENGTH_LONG).show()

                    buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                    progressBarKkeSearch.visibility = View.GONE
                    cari.text = "Cari"
                    cari.isEnabled = true
                }

                override fun onResponse(call: Call<ResponseKke>, response: Response<ResponseKke>) {
                    if (response.isSuccessful)
                    {
                        Log.d("response Berhasil",response.message())
                        val data =response.body()?.result
                        if (data?.size?:0>0)
                        {
                            Toast.makeText(dialog.context,"Data Ditemukan",Toast.LENGTH_LONG).show()
                            searcKke.adapter = AdapterKke(data,this@GetKkeActivity)
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarKkeSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                        else
                        {
                            Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarKkeSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                    }
                    else
                    {
                        Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                        buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                        progressBarKkeSearch.visibility = View.GONE
                        cari.text = "Cari"
                        cari.isEnabled = true
                    }
                }

            })
        }

        dialog.show()
    }
}