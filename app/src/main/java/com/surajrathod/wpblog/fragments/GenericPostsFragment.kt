package com.surajrathod.wpblog.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.databinding.FragmentGenericPostsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [GenericPostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        val id = arguments?.getInt("typeOfPost")
        id.let {
//            profile= myProfile.find { it.playerId==id }
            if(id!=null){
                val genericTxt = view.findViewById<TextView>(R.id.genericPosts)
                genericTxt.text="posts of category $id"
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