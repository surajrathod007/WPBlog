package com.surajrathod.wpblog.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.RecyclerViewPostAdapter
import com.surajrathod.wpblog.databinding.FragmentDashboardBinding
import com.surajrathod.wpblog.model.PostDetails

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var category0 : CardView
lateinit var category1 : CardView



/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<View>(R.id.postList)
        category0=view.findViewById(R.id.category0)
        category1=view.findViewById(R.id.category1)

        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = RecyclerViewPostAdapter(postList)
            }
        }

        category0.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("typeOfPost",0)
            findNavController().navigate(R.id.action_dashboardFragment_to_genericPostsFragment,bundle)
        }
        category1.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("typeOfPost",1)
            findNavController().navigate(R.id.action_dashboardFragment_to_genericPostsFragment,bundle)
        }
    }


}