package com.maad.ipapi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openMaps(view: View) {

        val button: Button = findViewById(R.id.btn)
        val progress: ProgressBar = findViewById(R.id.pb)
        button.visibility = View.INVISIBLE
        progress.visibility = View.VISIBLE

        val retrofit = Retrofit
            .Builder()
            .baseUrl("http://ip-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val callable = retrofit.create(CallableInterface::class.java)
        val ipModelCall = callable.getData()
        ipModelCall
            .enqueue(object : Callback<IPModel> {
                override fun onResponse(call: Call<IPModel>, response: Response<IPModel>) {
                    button.visibility = View.VISIBLE
                    progress.visibility = View.INVISIBLE
                    val ipModel: IPModel? = response.body()
                    Toast.makeText(
                        this@MainActivity,
                        "${ipModel?.country}, ${ipModel?.city}.", Toast.LENGTH_LONG
                    ).show()
                    val latLng = Uri.parse("geo:${ipModel?.latitude},${ipModel?.longitude}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, latLng)
                    startActivity(mapIntent)
                }

                override fun onFailure(call: Call<IPModel>, t: Throwable) {
                    button.visibility = View.VISIBLE
                    progress.visibility = View.INVISIBLE
                    Log.d("json", "Error: ${t.message}")
                }

            })

    }


}
