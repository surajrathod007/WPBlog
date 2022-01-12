package com.surajrathod.wpblog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.Database.PostEntity
import com.surajrathod.wpblog.databinding.CategoryViewholderBinding
import com.surajrathod.wpblog.fragments.GenericPostsFragmentDirections
import com.surajrathod.wpblog.fragments.postList
import com.surajrathod.wpblog.model.PostCategory
import com.surajrathod.wpblog.model.PostDetails

class CategoriesAdapter(val categoryList : List<PostCategory>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
   var datalist = postList as List<PostDetails>
    class ViewHolder(val binding : CategoryViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoty = categoryList[position]
        holder.binding.categoryName.text=categoty.category
        holder.binding.categoryName.setOnClickListener {
           /* datalist = postList.filter { categoty.id == it.id }
            RecyclerViewPostAdapter(datalist,1).update()*/
            Navigation.findNavController(it).navigate(GenericPostsFragmentDirections.actionGenericPostsFragmentSelf2(categoty.id))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}