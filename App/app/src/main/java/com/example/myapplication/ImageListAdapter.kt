package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageListAdapter(val imageList: List<ImageDocument>,
                       val onItemClick : (doc: ImageDocument)-> Unit)
    : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    inner class ViewHolder(val imageView: ImageView) :
        RecyclerView.ViewHolder(imageView) {
        fun bind(doc: ImageDocument){
            Glide.with(imageView).load(doc.thumbnail_url).into(imageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view as ImageView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doc = imageList[position]
        holder.bind(doc)
        holder.imageView.setOnClickListener {
            onItemClick(doc)
        }
    }
    override fun getItemCount(): Int = imageList.size
}