package com.example.completeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.completeapp.data.DataFetch
import com.example.completeapp.data.Tesla
import com.example.completeapp.data.TeslaData
import com.example.completeapp.fragments.FragmentA
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MyViewModel(private val teslaService: DataFetch): ViewModel() {
     private val mylivedata: MutableLiveData<Tesla> = MutableLiveData()
    val liveData: LiveData<Tesla> = mylivedata
    init {
        ApiData()
    }
    fun ApiData(){
        val teslacar = teslaService.ApiData("Tesla", 1)
        teslacar.enqueue(object: Callback,  retrofit2.Callback<Tesla>{
            public override fun onResponse(call: Call<Tesla>, response: Response<Tesla>) {
                mylivedata.postValue(response.body())
                if(mylivedata.value!=null){
                    Log.d("Got tesla", "Tesla is here")
                }
            }

            override fun onFailure(call: Call<Tesla>, t: Throwable) {
                Log.d("Didn't get Tesla", "No Tesla :( ")
            }
        })
    }
}