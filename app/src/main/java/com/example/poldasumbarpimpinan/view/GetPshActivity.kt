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
import com.example.poldasumbarpimpinan.adapter.AdapterPsh
import com.example.poldasumbarpimpinan.model.ResponsePsh
import com.example.poldasumbarpimpinan.model.ResultItemPsh
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import com.example.poldasumbarpimpinan.presenter.psh.PshPresenter
import com.example.poldasumbarpimpinan.presenter.psh.PshView
import kotlinx.android.synthetic.main.activity_get_psh.*
import kotlinx.android.synthetic.main.activity_get_psh.immgSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GetPshActivity : AppCompatActivity(), PshView {
    var presenter:PshPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_psh)

        presenter = PshPresenter(this)
        buttoTampil.setOnClickListener {
            showProgress()
            presenter?.getPsh()
        }

        immgSearch.setOnClickListener {
            showDialogSearch()
        }

    }

    override fun onSuccess(message: String, result: List<ResultItemPsh?>?) {
        toast("Berhasil")
        getPsh.adapter = AdapterPsh(result,this)

    }
    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideProgress() {
        progressBarPsh.visibility = View.GONE
    }

    override fun hideLoading() {
        buttoTampil.setBackgroundResource(R.drawable.bg_input_view)
        buttoTampil.text = "Tampilkan Data"
        buttoTampil.isEnabled = true
    }

    override fun dataKosong() {
        toast("Data Kosong")
    }


    fun toast(message: String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG)
    }
    fun showProgress()
    {
        buttoTampil.setBackgroundResource(R.drawable.bg_input_view_loading)
        progressBarPsh.visibility = View.VISIBLE
        buttoTampil.text = "Loading..."
        buttoTampil.isEnabled = false
    }

    private fun showDialogSearch() {

        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_item_search_psh)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val close = dialog.findViewById<ImageView>(R.id.imgClose)
        val cari = dialog.findViewById<Button>(R.id.buttonCari)
        val kesatuan = dialog.findViewById<EditText>(R.id.edtKesatuan)
        val nama = dialog.findViewById<EditText>(R.id.edtNamaTerduga)
        val searchPsh = dialog.findViewById<RecyclerView>(R.id.getPshSearch)
        val progressBarPshSearch = dialog.findViewById<ProgressBar>(R.id.progressBarSearch)
        val buttoCari = dialog.findViewById<Button>(R.id.buttonCari)

        close.setOnClickListener {
            dialog.dismiss()
        }

        cari.setOnClickListener {

            buttoCari.setBackgroundResource(R.drawable.bg_input_view_loading)
            progressBarPshSearch.visibility = View.VISIBLE
            cari.text = "Loading..."
            cari.isEnabled = false

            val Txtkesatuan = kesatuan.text.toString()
            val TxtNama = nama.text.toString()

            ConfigNetwork.getRetrofit().searchPsh(Txtkesatuan, TxtNama).enqueue(object :
                Callback<ResponsePsh> {
                override fun onFailure(call: Call<ResponsePsh>, t: Throwable) {
                    Log.d("response gagal",t.localizedMessage)
                    Toast.makeText(dialog.context,"Gagal",Toast.LENGTH_LONG).show()

                    buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                    progressBarPshSearch.visibility = View.GONE
                    cari.text = "Cari"
                    cari.isEnabled = true
                }

                override fun onResponse(call: Call<ResponsePsh>, response: Response<ResponsePsh>) {
                    if (response.isSuccessful)
                    {
                        Log.d("response Berhasil",response.message())
                        val data =response.body()?.result
                        if (data?.size?:0>0)
                        {
                            Toast.makeText(dialog.context,"Data Ditemukan",Toast.LENGTH_LONG).show()
                            searchPsh.adapter = AdapterPsh(data,this@GetPshActivity)
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarPshSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                        else
                        {
                            Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarPshSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                    }
                    else
                    {
                        Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                        buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                        progressBarPshSearch.visibility = View.GONE
                        cari.text = "Cari"
                        cari.isEnabled = true
                    }
                }

            })
        }

        dialog.show()
    }
}