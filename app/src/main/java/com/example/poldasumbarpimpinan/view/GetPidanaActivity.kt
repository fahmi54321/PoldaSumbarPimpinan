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
import com.example.poldasumbarpimpinan.adapter.AdapterPidana
import com.example.poldasumbarpimpinan.model.ResponsePidana
import com.example.poldasumbarpimpinan.model.ResultItemPidana
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import com.example.poldasumbarpimpinan.presenter.pidana.PidanaPresenter
import com.example.poldasumbarpimpinan.presenter.pidana.PidanaView
import kotlinx.android.synthetic.main.activity_get_pidana.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GetPidanaActivity : AppCompatActivity(), PidanaView {
    var presenter:PidanaPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pidana)

        presenter = PidanaPresenter(this)
        buttonKesatuan.setOnClickListener {
            showProgress()
            presenter?.getPidana()
        }

        immgSearch.setOnClickListener {
            showDialogSearch()
        }

    }

    override fun onSuccess(message: String, result: List<ResultItemPidana?>?) {
        toast("Berhasil")
        getPidana.adapter = AdapterPidana(result,this)
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideProgress() {
        progressBarPidana.visibility = View.GONE
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
        progressBarPidana.visibility = View.VISIBLE
        buttonKesatuan.text = "Loading..."
        buttonKesatuan.isEnabled = false
    }

    private fun showDialogSearch() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_item_search_pidana)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val close = dialog.findViewById<ImageView>(R.id.imgClose)
        val cari = dialog.findViewById<Button>(R.id.buttonCari)
        val kesatuan = dialog.findViewById<EditText>(R.id.edtKesatuan)
        val nama_pelaku = dialog.findViewById<EditText>(R.id.edtNamaPelaku)
        val searcPidana = dialog.findViewById<RecyclerView>(R.id.getPidanaSearch)
        val progressBarPidanaSearch = dialog.findViewById<ProgressBar>(R.id.progressBarSearch)
        val buttoCari = dialog.findViewById<Button>(R.id.buttonCari)

        close.setOnClickListener {
            dialog.dismiss()
        }

        cari.setOnClickListener {

            buttoCari.setBackgroundResource(R.drawable.bg_input_view_loading)
            progressBarPidanaSearch.visibility = View.VISIBLE
            cari.text = "Loading..."
            cari.isEnabled = false

            val Txtkesatuan = kesatuan.text.toString()
            val TxtNama_Pelaku = nama_pelaku.text.toString()

            ConfigNetwork.getRetrofit().searchPidana(Txtkesatuan, TxtNama_Pelaku).enqueue(object :
                Callback<ResponsePidana> {
                override fun onFailure(call: Call<ResponsePidana>, t: Throwable) {
                    Log.d("response gagal",t.localizedMessage)
                    Toast.makeText(dialog.context,"Gagal",Toast.LENGTH_LONG).show()

                    buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                    progressBarPidanaSearch.visibility = View.GONE
                    cari.text = "Cari"
                    cari.isEnabled = true
                }

                override fun onResponse(call: Call<ResponsePidana>, response: Response<ResponsePidana>) {
                    if (response.isSuccessful)
                    {
                        Log.d("response Berhasil",response.message())
                        val data =response.body()?.result
                        if (data?.size?:0>0)
                        {
                            Toast.makeText(dialog.context,"Data Ditemukan",Toast.LENGTH_LONG).show()
                            searcPidana.adapter = AdapterPidana(data,this@GetPidanaActivity)
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarPidanaSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                        else
                        {
                            Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarPidanaSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                    }
                    else
                    {
                        Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                        buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                        progressBarPidanaSearch.visibility = View.GONE
                        cari.text = "Cari"
                        cari.isEnabled = true
                    }
                }

            })
        }

        dialog.show()
    }

}