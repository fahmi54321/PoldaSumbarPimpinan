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
import com.example.poldasumbarpimpinan.adapter.AdapterPeraturan
import com.example.poldasumbarpimpinan.model.ResponsePeraturan
import com.example.poldasumbarpimpinan.model.ResultItemPeraturan
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import com.example.poldasumbarpimpinan.presenter.peraturan.PeraturanPresenter
import com.example.poldasumbarpimpinan.presenter.peraturan.PeraturanView
import kotlinx.android.synthetic.main.activity_get_peraturan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GetPeraturanActivity : AppCompatActivity(), PeraturanView {
    var presenter:PeraturanPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_peraturan)

        presenter = PeraturanPresenter(this)
        buttonKesatuan.setOnClickListener {
            showProgress()
            presenter?.getPeraturan()
        }

        immgSearch.setOnClickListener {
            showDialogSearch()
        }
    }

    override fun onSuccess(message: String, result: List<ResultItemPeraturan?>?) {
        toast("Berhasil")
        getPeraturan.adapter = AdapterPeraturan(result,this)
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideProgress() {
        progressBarPeraturan.visibility = View.GONE
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
        progressBarPeraturan.visibility = View.VISIBLE
        buttonKesatuan.text = "Loading..."
        buttonKesatuan.isEnabled = false
    }

    private fun showDialogSearch() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_item_search_peraturan)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val close = dialog.findViewById<ImageView>(R.id.imgClose)
        val cari = dialog.findViewById<Button>(R.id.buttonCari)
        val pilih_peraturan = dialog.findViewById<EditText>(R.id.edtPilihPeraturan)
        val nama_pejabat = dialog.findViewById<EditText>(R.id.edtNamaPejabat)
        val searcPeraturan = dialog.findViewById<RecyclerView>(R.id.getPeraturanSearch)
        val progressBarPeraturanSearch = dialog.findViewById<ProgressBar>(R.id.progressBarSearch)
        val buttoCari = dialog.findViewById<Button>(R.id.buttonCari)

        close.setOnClickListener {
            dialog.dismiss()
        }

        cari.setOnClickListener {

            buttoCari.setBackgroundResource(R.drawable.bg_input_view_loading)
            progressBarPeraturanSearch.visibility = View.VISIBLE
            cari.text = "Loading..."
            cari.isEnabled = false

            val TxtPilihPeraturan = pilih_peraturan.text.toString()
            val TxtNamaPejabat = nama_pejabat.text.toString()

            ConfigNetwork.getRetrofit().searchPeraturan(TxtPilihPeraturan, TxtNamaPejabat).enqueue(object :
                Callback<ResponsePeraturan> {
                override fun onFailure(call: Call<ResponsePeraturan>, t: Throwable) {
                    Log.d("response gagal",t.localizedMessage)
                    Toast.makeText(dialog.context,"Gagal",Toast.LENGTH_LONG).show()

                    buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                    progressBarPeraturanSearch.visibility = View.GONE
                    cari.text = "Cari"
                    cari.isEnabled = true
                }

                override fun onResponse(call: Call<ResponsePeraturan>, response: Response<ResponsePeraturan>) {
                    if (response.isSuccessful)
                    {
                        Log.d("response Berhasil",response.message())
                        val data =response.body()?.result
                        if (data?.size?:0>0)
                        {
                            Toast.makeText(dialog.context,"Data Ditemukan",Toast.LENGTH_LONG).show()
                            searcPeraturan.adapter = AdapterPeraturan(data,this@GetPeraturanActivity)
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarPeraturanSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                        else
                        {
                            Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarPeraturanSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                    }
                    else
                    {
                        Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                        buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                        progressBarPeraturanSearch.visibility = View.GONE
                        cari.text = "Cari"
                        cari.isEnabled = true
                    }
                }

            })
        }

        dialog.show()
    }
}