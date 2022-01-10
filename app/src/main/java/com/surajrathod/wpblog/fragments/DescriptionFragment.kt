package com.surajrathod.wpblog.fragments

import android.os.Bundle


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs


import com.surajrathod.wpblog.R


class DescriptionFragment : Fragment() {



lateinit var postTitle : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_description, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //JUST TO TRY OUT IF SAFEARGS WORKING YOU CAN REMOVE IT LATER
        /*postTitle=view.findViewById(R.id.txtPostTitle)
        arguments?.let {
            val args = DescriptionFragmentArgs.fromBundle(it)
            val postId=args.postId
            val post = postList.find { postId == it.id }
            post?.let {
                with(it) {
                   postTitle.text =title
                }
            }
        }*/
        val post by navArgs<DescriptionFragmentArgs>()
        println(post)
    }


}