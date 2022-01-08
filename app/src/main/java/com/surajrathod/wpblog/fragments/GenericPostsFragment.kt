package com.surajrathod.wpblog.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.RecyclerViewPostAdapter
import com.surajrathod.wpblog.databinding.FragmentGenericPostsBinding
import com.surajrathod.wpblog.model.PostDetails

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [GenericPostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenericPostsFragment : Fragment() {

    val postList = arrayListOf<PostDetails>(
        PostDetails(1,"title1","img1","1-1-22","tech","desc","url1"),
        PostDetails(2,"title2","img1","1-1-23","tech","desc","url1"),
        PostDetails(3,"title3","img1","4-1-24","sports","desc","url1")
    )

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
        val id = arguments?.getInt("typeOfPost")
        id.let {
//            profile= myProfile.find { it.playerId==id }
            if(id!=null){
//                val genericTxt = view.findViewById<TextView>(R.id.genericPosts)
//                genericTxt.text="posts of category $id"
            }

        }
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = RecyclerViewPostAdapter(postList,2)
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