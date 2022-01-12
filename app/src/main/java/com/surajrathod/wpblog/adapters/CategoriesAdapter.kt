package com.surajrathod.wpblog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.databinding.CategoryViewholderBinding
import com.surajrathod.wpblog.fragments.DashboardFragmentDirections
import com.surajrathod.wpblog.fragments.fetchedPostList
import com.surajrathod.wpblog.model.PostCategory
import com.surajrathod.wpblog.model.PostDetails

class CategoriesAdapter(val categoryList : List<PostCategory>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
   var datalist = fetchedPostList as List<PostDetails>
    class ViewHolder(val binding : CategoryViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoty = categoryList[position]
        holder.binding.categoryName.text=categoty.category
        holder.binding.categoryName.setOnClickListener {
            Navigation.findNavController(it).navigate(DashboardFragmentDirections.actionDashboardFragmentToGenericPostsFragment(categoty.id))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}