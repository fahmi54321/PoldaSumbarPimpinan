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
import com.example.poldasumbarpimpinan.adapter.AdapterDisiplin
import com.example.poldasumbarpimpinan.model.ResponseDisiplin
import com.example.poldasumbarpimpinan.model.ResultItemDisiplin
import com.example.poldasumbarpimpinan.network.ConfigNetwork
import com.example.poldasumbarpimpinan.presenter.disiplin.DisiplinPresenter
import com.example.poldasumbarpimpinan.presenter.disiplin.DisiplinView
import kotlinx.android.synthetic.main.activity_get_disiplin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class GetDisiplinActivity : AppCompatActivity(), DisiplinView {

    var presenter:DisiplinPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_disiplin)

        presenter = DisiplinPresenter(this)
        buttonKesatuan.setOnClickListener {
            showProgress()
            presenter?.getDisiplin()

        }

        immgSearch.setOnClickListener {
            showDialogSearch()
        }

    }



    override fun onSuccess(message: String, result: List<ResultItemDisiplin?>?) {
        toast("Berhasil")
        getDisiplin.adapter = AdapterDisiplin(result,this)
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideProgress() {
        progressBarDisiplin.visibility = View.GONE
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
        progressBarDisiplin.visibility = View.VISIBLE
        buttonKesatuan.text = "Loading..."
        buttonKesatuan.isEnabled = false
    }

    private fun showDialogSearch() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_item_search_disiplin)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val close = dialog.findViewById<ImageView>(R.id.imgClose)
        val cari = dialog.findViewById<Button>(R.id.buttonCari)
        val kesatuan = dialog.findViewById<EditText>(R.id.edtKesatuan)
        val nama_pelaku = dialog.findViewById<EditText>(R.id.edtNamaPelaku)
        val searchDisiplin = dialog.findViewById<RecyclerView>(R.id.getDisiplinSearch)
        val progressBarDisiplinSearch = dialog.findViewById<ProgressBar>(R.id.progressBarSearch)
        val buttoCari = dialog.findViewById<Button>(R.id.buttonCari)

        close.setOnClickListener {
            dialog.dismiss()
        }

        cari.setOnClickListener {

            buttoCari.setBackgroundResource(R.drawable.bg_input_view_loading)
            progressBarDisiplinSearch.visibility = View.VISIBLE
            cari.text = "Loading..."
            cari.isEnabled = false

            val Txtkesatuan = kesatuan.text.toString()
            val TxtNama_Pelaku = nama_pelaku.text.toString()

            ConfigNetwork.getRetrofit().searchDisiplin(Txtkesatuan, TxtNama_Pelaku).enqueue(object :
                Callback<ResponseDisiplin> {
                override fun onFailure(call: Call<ResponseDisiplin>, t: Throwable) {
                    Log.d("response gagal",t.localizedMessage)
                    Toast.makeText(dialog.context,"Gagal",Toast.LENGTH_LONG).show()

                    buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                    progressBarDisiplinSearch.visibility = View.GONE
                    cari.text = "Cari"
                    cari.isEnabled = true
                }

                override fun onResponse(call: Call<ResponseDisiplin>, response: Response<ResponseDisiplin>) {
                    if (response.isSuccessful)
                    {
                        Log.d("response Berhasil",response.message())
                        val data =response.body()?.result
                        if (data?.size?:0>0)
                        {
                            Toast.makeText(dialog.context,"Data Ditemukan",Toast.LENGTH_LONG).show()
                            searchDisiplin.adapter = AdapterDisiplin(data,this@GetDisiplinActivity)
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarDisiplinSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                        else
                        {
                            Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                            buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                            progressBarDisiplinSearch.visibility = View.GONE
                            cari.text = "Cari"
                            cari.isEnabled = true
                        }
                    }
                    else
                    {
                        Toast.makeText(dialog.context,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
                        buttoCari.setBackgroundResource(R.drawable.bg_input_view)
                        progressBarDisiplinSearch.visibility = View.GONE
                        cari.text = "Cari"
                        cari.isEnabled = true
                    }
                }

            })
        }

        dialog.show()
    }
}