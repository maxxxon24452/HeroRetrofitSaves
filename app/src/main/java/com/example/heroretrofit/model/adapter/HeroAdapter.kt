package com.example.heroretrofit.model.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heroretrofit.R
import com.example.heroretrofit.databinding.CardViewDesignBinding
import com.example.heroretrofit.model.data.HeroItem

import com.squareup.picasso.Picasso

class HeroAdapter(
    private val mList: List<HeroItem>,
    val mItemClickListener: ItemClickListener
) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onClickItem(position: Int, hero: HeroItem)
    }
    fun setData(newData: String?) {

    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList?.get(position)

        Picasso.get().load("${mList?.get(position)?.images?.lg}")
            .into(holder.binding.PhotoHero)
        Log.d("mylog"," ${mList?.get(position)?.id}")
        holder.binding.nameHero.text = mList?.get(position)?.name
        holder.binding.PhotoHero.setOnClickListener{
            mItemClickListener.onClickItem(position, hero = mList.get(position))
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val binding = CardViewDesignBinding.bind(itemView)

    }
}