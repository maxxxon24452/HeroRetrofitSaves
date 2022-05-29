package com.example.heroretrofit.activty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.heroretrofit.model.api.ApiInterface
import com.example.heroretrofit.model.adapter.HeroAdapter
import com.example.heroretrofit.constant.Constant.ID_HERO

import com.example.heroretrofit.model.data.Hero
import com.example.heroretrofit.databinding.ActivityMainBinding
import com.example.heroretrofit.hero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainActivity : AppCompatActivity(), HeroAdapter.ItemClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DotaHeroy()
        binding.recyclerview.layoutManager = GridLayoutManager(this@MainActivity,3)
    }
    private fun DotaHeroy(){

        val api = ApiInterface.create()

        val apiHero = api.getMovies(1)

        apiHero.enqueue(object : Callback<Hero> {
                override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
                    hero = listOf(response.body()) as List<Hero>
                    Log.d("testLogs","OnResponse Success $hero")

                    binding.recyclerview.adapter = HeroAdapter(this@MainActivity,
                        hero
                    )
                }

                override fun onFailure(call: Call<Hero>, t: Throwable) {
                    TODO("Not yet implemented")
                }



            })


    }

    override fun onClickItem(position: Int, hero: List<Hero>) {
        val intent = Intent(this@MainActivity, HeroDetails::class.java)
        intent.putExtra(ID_HERO,position)
        startActivity(intent)
    }


}






