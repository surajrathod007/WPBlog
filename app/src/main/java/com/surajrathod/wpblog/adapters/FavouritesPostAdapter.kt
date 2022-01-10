package com.surajrathod.wpblog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.Database.PostEntity
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.FavouritesPostAdapter.ViewHolder
import com.surajrathod.wpblog.databinding.PostViewHolderBinding

class FavouritesPostAdapter(var context : Context) : RecyclerView.Adapter<FavouritesPostAdapter.ViewHolder>(){


    var dataList = emptyList<PostEntity>()

    internal fun setDataList(dataList : List<PostEntity>){
        this.dataList = dataList
        notifyDataSetChanged()
    }



    lateinit var view : View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.post_view_holder,parent,false)
        return ViewHolder(
            PostViewHolderBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    inner class ViewHolder(val binding : PostViewHolderBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var data = dataList[position]

        holder.binding.title.text = data.postTitle
        //holder.binding.category.text = data.postCategory
        holder.binding.date.text = data.postDate




    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}