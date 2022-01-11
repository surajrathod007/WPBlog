package com.surajrathod.wpblog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.databinding.CategoryViewholderBinding
import com.surajrathod.wpblog.model.PostCategory

class CategoriesAdapter(val categoryList : ArrayList<PostCategory>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(val binding : CategoryViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoty = categoryList[position]
        holder.binding.categoryName.text=categoty.category
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}