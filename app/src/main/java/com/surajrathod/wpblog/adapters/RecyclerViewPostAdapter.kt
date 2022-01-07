package com.surajrathod.wpblog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.databinding.PostViewHolderBinding
import com.surajrathod.wpblog.model.PostDetails

class RecyclerViewPostAdapter (val postList : ArrayList<PostDetails>) : RecyclerView.Adapter<RecyclerViewPostAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
                PostViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]
        holder.binding.title.text=post.title
//        holder.binding.poster.setImageResource()
        holder.binding.date.text=post.date
        holder.binding.category.text=post.category
    }

    inner class ViewHolder(val binding : PostViewHolderBinding) : RecyclerView.ViewHolder(binding.root)
}