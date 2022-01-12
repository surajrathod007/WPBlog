package com.surajrathod.wpblog.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.RecyclerViewPostAdapter


class GenericPostsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_generic_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<View>(R.id.postList)
        arguments?.let{
            val args = GenericPostsFragmentArgs.fromBundle(it)
            if (recyclerView is RecyclerView) {
                with(recyclerView) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewPostAdapter(fetchedPostList.filter { it.category == args.postCategory },1)
                }
            }
        }
    }
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment GenericPostsFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            GenericPostsFragment().apply {
//                arguments = Bundle().apply {
//
//                }
//            }
//    }
}