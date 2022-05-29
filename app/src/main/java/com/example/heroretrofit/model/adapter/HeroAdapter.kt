package com.example.heroretrofit.model.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.heroretrofit.R
import com.example.heroretrofit.model.data.Hero
import com.example.heroretrofit.databinding.CardViewDesignBinding

import com.squareup.picasso.Picasso

class HeroAdapter(
    val hItemClickListener: ItemClickListener,
    private val hList: List<Hero>

) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {


    interface ItemClickListener {
        fun onClickItem(position: Int,hero: List<Hero>)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load("${hList[position].image.url}").into(holder.binding.PhotoHero)
        Log.d("mylog"," ${hList[position].name}")
        holder.binding.nameHero.text = hList[position].name
        holder.binding.PhotoHero.setOnClickListener{
            hItemClickListener.onClickItem(position, hero = hList)
        }


    }

    override fun getItemCount(): Int {
        return hList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val binding = CardViewDesignBinding.bind(itemView)

    }

}