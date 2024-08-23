package com.chaeni__beam.ai_healing.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chaeni__beam.ai_healing.Data.foodData
import com.chaeni__beam.ai_healing.R

class FoodListAdapter(private val context: Context) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    var datas = mutableListOf<foodData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val foodName: TextView = itemView.findViewById(R.id.food_name)
        private val foodImg: ImageView = itemView.findViewById(R.id.food_img)

        fun bind(item: foodData) {
            foodName.text = item.food_name
            Glide.with(itemView).load(item.food_img).into(foodImg)

        }
    }


}
