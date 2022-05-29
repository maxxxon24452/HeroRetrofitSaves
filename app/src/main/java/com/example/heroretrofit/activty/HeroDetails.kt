package com.example.heroretrofit.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.heroretrofit.constant.Constant.HERO
import com.example.heroretrofit.constant.Constant.PHOTO
import com.example.heroretrofit.constant.Constant.POWER
import com.example.heroretrofit.constant.Constant.SPEED
import com.example.heroretrofit.databinding.ActivityHeroDetailsBinding
import com.example.heroretrofit.hero
import com.squareup.picasso.Picasso

class HeroDetails : AppCompatActivity() {
    lateinit var binding: ActivityHeroDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val heroname = intent.getStringExtra(HERO)
        val herophoto = intent.getStringExtra(PHOTO)
        val herospeed = intent.getIntExtra(SPEED,0)
        val heropower = intent.getIntExtra(POWER,0)
        with(binding){
            Picasso.get().load("${herophoto}").into(heroImageDetails)
            heroNameDetails.text = heroname
            speedDetails.text = "скорость: ${herospeed}"
            baseAttackMaxDetails.text = "атака: ${heropower}"
        }

    }
}