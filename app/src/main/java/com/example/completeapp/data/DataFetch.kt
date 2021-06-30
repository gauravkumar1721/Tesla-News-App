package com.example.completeapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://newsapi.org/"
const val API_KEY =  "439038a05fb24ce29b3cfbe96726ada0"
interface DataFetch {
    //@GET("/v2/top-headlines?apiKey=$API_KEY")
    //fun getHeadlines(@Query("country")country: String, @Query("page")page: Int): Call<News>
    //https://newsapi.org/v2/everything?q=tesla&from=2021-05-29&apiKey=439038a05fb24ce29b3cfbe96726ada0
    @GET("/v2/everything?apiKey=$API_KEY")
    fun ApiData(@Query("q") Tesla: String, @Query("page")page : Int): Call<Tesla>

    companion object {
        fun getInstance(): DataFetch{
            val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            val DataFetchInstance: DataFetch
            val retrofit = Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            DataFetchInstance = retrofit.create(DataFetch::class.java)
            return DataFetchInstance
        }

    }
}
//https://newsapi.org/v2/everything?q=tesla&from=2021-05-29&apiKey=439038a05fb24ce29b3cfbe96726ada0
//https://newsapi.org/v2/everything?apiKey=439038a05fb24ce29b3cfbe96726ada0&company=Tesla&date=2021-05-28