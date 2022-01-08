package com.surajrathod.wpblog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.databinding.PostViewHolderBinding
import com.surajrathod.wpblog.model.PostDetails

class RecyclerViewPostAdapter (val postList : ArrayList<PostDetails>,val type : Int) : RecyclerView.Adapter<RecyclerViewPostAdapter.ViewHolder>(){

    lateinit var view : View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view =LayoutInflater.from(parent.context).inflate(R.layout.post_view_holder,parent,false)
        return ViewHolder(
            PostViewHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(type==2) print("this category")
        val post = postList[position]
        holder.binding.title.text=post.title
//        holder.binding.poster.setImageResource()
        holder.binding.date.text=post.date
        holder.binding.category.text=post.category
        holder.binding.postItem.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.descriptionFragment)
        }
    }

    inner class ViewHolder(val binding : PostViewHolderBinding) : RecyclerView.ViewHolder(binding.root)
}