package com.nitish.viewpagerapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class DotsRecycler(var context: Context, var count: Int, var selectedPosition: Int) :
    RecyclerView.Adapter<DotsRecycler.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotsRecycler.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_dots, parent, false)
        return ViewHolder(view.rootView)

    }

    override fun onBindViewHolder(holder: DotsRecycler.ViewHolder, position: Int) {
        if(position == selectedPosition){
            holder.imageView.setColorFilter(context.getColor(R.color.teal_200))
        }else{
            holder.imageView.setColorFilter(context.getColor(R.color.purple_200))

        }
    }

    override fun getItemCount(): Int {
        return count
    }

    fun updatePosition(position: Int){
        selectedPosition = position
        notifyDataSetChanged()
    }
}