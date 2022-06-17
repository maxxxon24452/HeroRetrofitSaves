package com.example.heroretrofit.activty

import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.heroretrofit.constant.Constant.HERO
import com.example.heroretrofit.model.api.ApiInterface
import com.example.heroretrofit.model.adapter.HeroAdapter
import com.example.heroretrofit.constant.Constant.PHOTO
import com.example.heroretrofit.constant.Constant.POWER
import com.example.heroretrofit.constant.Constant.SPEED
import com.example.heroretrofit.model.data.Hero
import com.example.heroretrofit.databinding.ActivityMainBinding
import com.example.heroretrofit.model.data.HeroItem
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainActivity : AppCompatActivity(), HeroAdapter.ItemClickListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var json:List<HeroItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DotaHeroy()
        binding.recyclerview.layoutManager = GridLayoutManager(this@MainActivity, 3)
    }

    private fun DotaHeroy() {

        val apiInterface = ApiInterface.create().getMovies("all.json")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(object : Callback<Hero>, HeroAdapter.ItemClickListener {
            override fun onResponse(call: Call<Hero>?, response: Response<Hero>?) {
                Log.d("testLogs", "OnResponse Success ${response?.body()}")
                // This will pass the ArrayList to our Adapter
                //запись в sharedpref
                val gson = Gson()
                val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("STRING_KEY", gson.toJson(response?.body()))
                }.apply()
                // чтение sharedprefs
                var savedString: String? = sharedPreferences.getString("STRING_KEY", null)
                Log.d("testLogsin", "OnResponse Success $savedString")


                if (savedString != null) {
                    json = gson.fromJson(
                        savedString,
                        Array<HeroItem>::class.java
                    ).asList()

                }

                val adapter = HeroAdapter(json as MutableList<HeroItem>, this@MainActivity)
                // Setting the Adapter with the recyclerview
                binding.recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<Hero>?, t: Throwable?) {
                Log.d("testLogs", "onFailure  ${t?.message}")
            }

            override fun onClickItem(position: Int, hero: HeroItem) {
                val intent = Intent(this@MainActivity, HeroDetails::class.java)
                intent.putExtra(HERO, hero.name)
                intent.putExtra(PHOTO, hero.images.lg)
                intent.putExtra(SPEED, hero.powerstats.speed)
                intent.putExtra(POWER, hero.powerstats.power)
                startActivity(intent)
            }


        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

    override fun onClickItem(position: Int, hero: HeroItem) {
        val intent = Intent(this@MainActivity, HeroDetails::class.java)
        intent.putExtra(HERO, hero.name)
        intent.putExtra(PHOTO, hero.images.lg)
        intent.putExtra(SPEED, hero.powerstats.speed)
        intent.putExtra(POWER, hero.powerstats.power)
        startActivity(intent)
    }


}









