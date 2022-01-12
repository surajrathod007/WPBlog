package com.surajrathod.wpblog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.databinding.PostViewHolderBinding
import com.surajrathod.wpblog.fragments.DashboardFragmentDirections
import com.surajrathod.wpblog.fragments.GenericPostsFragmentDirections
import com.surajrathod.wpblog.fragments.fetchedCategoryList
import com.surajrathod.wpblog.model.PostDetails

class RecyclerViewPostAdapter (val postList : List<PostDetails>,val type : Int) : RecyclerView.Adapter<RecyclerViewPostAdapter.ViewHolder>(){

    lateinit var view : View
    private var PostList = arrayListOf<PostDetails>()
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

        val post = postList[position]
        holder.binding.title.text=post.title
        Picasso.get().load(post.img).into(holder.binding.poster)
        holder.binding.date.text=post.date
        val category = fetchedCategoryList.find { post.category == it.id }
        if (category != null) {
            holder.binding.category.text=category.category
        }
        holder.binding.postItem.setOnClickListener{
            if(type==0)
            Navigation.findNavController(it).navigate(DashboardFragmentDirections.actionDashboardFragmentToDescriptionFragment(post))
            else
                Navigation.findNavController(it).navigate(GenericPostsFragmentDirections.actionGenericPostsFragmentToDescriptionFragment(post))
        }
    }

    inner class ViewHolder(val binding : PostViewHolderBinding) : RecyclerView.ViewHolder(binding.root)

    fun update(){
        notifyDataSetChanged()
    }
}