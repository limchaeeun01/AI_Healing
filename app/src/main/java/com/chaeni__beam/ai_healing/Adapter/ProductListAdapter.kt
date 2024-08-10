package com.chaeni__beam.ai_healing.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chaeni__beam.ai_healing.R

class ProductListAdapter(private val context: Context) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var datas = mutableListOf<ProductData>()

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: ProductData)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val productName: TextView = itemView.findViewById(R.id.food_name)
        private val productImg: ImageView = itemView.findViewById(R.id.food_img)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(datas[position])
                }
            }
        }

        fun bind(item: ProductData) {
            productName.text = item.product_name
            Glide.with(itemView).load(item.product_img).into(productImg)

        }
    }

}