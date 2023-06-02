package com.app.androidretrofitpraticwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.androidretrofitpraticwork.Adapter.ListAdapter
import com.app.androidretrofitpraticwork.apiclient.ApiClient
import com.app.androidretrofitpraticwork.databinding.ActivityMainBinding
import com.app.androidretrofitpraticwork.model.UserData
import com.app.androidretrofitpraticwork.model.api.ApiService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var madapter:ListAdapter
    lateinit var service: ApiService
    lateinit var retrofit: Retrofit
    private var moveilist= mutableListOf<UserData>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit=Retrofit.Builder()
            .baseUrl("http://www.howtodoandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            service=retrofit.create(ApiService::class.java)


        binding.processBar.visibility=View.VISIBLE





        madapter=ListAdapter(this,moveilist )
        binding.recyclerView.layoutManager=GridLayoutManager(this,2)
        binding.recyclerView.adapter=madapter

        loaduser()












    }

    private fun loaduser() {

        binding.processBar.visibility=View.GONE
        service.getUserList().enqueue(object :Callback<MutableList<UserData>>{
            override fun onResponse(
                call: Call<MutableList<UserData>>,
                response: Response<MutableList<UserData>>
            ) {
                if (response.isSuccessful){
                    var res=response.body()
                    moveilist=res!!
                    madapter.setitem(moveilist)
                }
            }

            override fun onFailure(call: Call<MutableList<UserData>>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }


        })
    }


}