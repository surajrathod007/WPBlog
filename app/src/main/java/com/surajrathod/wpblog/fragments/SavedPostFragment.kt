package com.surajrathod.wpblog.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.Database.PostDatabase
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.FavouritesPostAdapter
import com.surajrathod.wpblog.databinding.FragmentSavedPostBinding


class SavedPostFragment : Fragment() {


    lateinit var db : PostDatabase
    lateinit var recyclerView: RecyclerView
    lateinit var adapter : FavouritesPostAdapter
    lateinit var binding : FragmentSavedPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_saved_post, container, false)

        binding = FragmentSavedPostBinding.bind(view)


        db = PostDatabase.getDatabase(requireContext())

        recyclerView = binding.savedPostRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity as Context)
        adapter = FavouritesPostAdapter(activity as Context)
        recyclerView.adapter = adapter


        db.PostDao().getAllPost().observe(viewLifecycleOwner, Observer {post->
            adapter.setDataList(post)

        })



        return view
    }



}