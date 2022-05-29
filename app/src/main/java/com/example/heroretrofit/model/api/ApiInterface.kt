package com.example.heroretrofit.model.api

import com.example.heroretrofit.model.data.Hero
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("all.json")
    fun getMovies(@Query("apikey")  sort: String): Call<Hero>



    companion object {

        var BASE_URL = "https://akabab.github.io/superhero-api/api/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}