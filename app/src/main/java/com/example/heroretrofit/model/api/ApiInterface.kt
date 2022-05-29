package com.example.heroretrofit.model.api

import com.example.heroretrofit.model.data.Hero
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/3202191630032155/{id}")
    fun getMovies(@Path("id") sort: Int): Call<Hero>



    companion object {

        var BASE_URL = "https://www.superheroapi.com/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}