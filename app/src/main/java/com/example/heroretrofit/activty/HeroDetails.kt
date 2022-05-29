package com.example.heroretrofit.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.heroretrofit.constant.Constant.ID_HERO
import com.example.heroretrofit.databinding.ActivityHeroDetailsBinding
import com.example.heroretrofit.hero
import com.squareup.picasso.Picasso

class HeroDetails : AppCompatActivity() {
    lateinit var binding: ActivityHeroDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val heroId = intent.getIntExtra(ID_HERO, 0)
        with(binding){
            heroNameDetails.text = hero[heroId].name
            Picasso.get().load("${hero[heroId].image.url}").into(heroImageDetails)
            speedDetails.text = "cкорость: ${hero[heroId].powerstats.speed}"
            baseHealthDetails.text = "power: ${hero[heroId].powerstats.power}"
            baseAttackMaxDetails.text = "rase: ${hero[heroId].appearance.race}"
            baseMana.text = "weight: ${hero[heroId].appearance.weight}"
        }

    }
}