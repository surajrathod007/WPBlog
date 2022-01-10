package com.surajrathod.wpblog.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.text.trimmedLength
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.RecyclerViewPostAdapter
import com.surajrathod.wpblog.databinding.FragmentDashboardBinding
import com.surajrathod.wpblog.internet.InternetStatus
import com.surajrathod.wpblog.model.PostCategory
import com.surajrathod.wpblog.model.PostDetails


val postList = arrayListOf<PostDetails>()
val categoryList = arrayListOf<PostCategory>()
lateinit var category0 : CardView
lateinit var category1 : CardView
class DashboardFragment : Fragment() {


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
        val view =inflater.inflate(R.layout.fragment_dashboard, container, false)

        val binding = FragmentDashboardBinding.bind(view)
        val recyclerView = view.findViewById<View>(R.id.postList)
        if(InternetStatus().checkForInternet(activity as Context)) {
            val queuePosts = Volley.newRequestQueue(activity as Context)
            val queueCategories = Volley.newRequestQueue(activity as Context)
            val urlPosts = "https://surajtutz.000webhostapp.com/wp-json/wp/v2/posts"
            val urlCategories = "https://surajtutz.000webhostapp.com/wp-json/wp/v2/categories"
            val requestForCategory = object : JsonArrayRequest(Request.Method.GET,urlCategories,null, Response.Listener {
                println("Category API success $it")
                for (i in 0 until it.length()){
                    val jsonObject = it.getJSONObject(i)
                    val category = PostCategory(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name")
                    )
                    categoryList.add(category)
                }
            },Response.ErrorListener {

            }){

            }
            queueCategories.add(requestForCategory)
            val requestForPosts =
                object : JsonArrayRequest(Request.Method.GET, urlPosts, null, Response.Listener {
                    println("Post API success $it")


                    for (i in 0 until it.length()) {
                        val jsonObjectPostDetails = it.getJSONObject(i)
                        val postDetails = PostDetails(
                            jsonObjectPostDetails.getInt("id"),
                            jsonObjectPostDetails.getJSONObject("title").getString("rendered"),
                            jsonObjectPostDetails.getString("jetpack_featured_media_url"),
                            jsonObjectPostDetails.getString("date").dropLast(9),
                            jsonObjectPostDetails.getJSONArray("categories").getInt(0),
                            jsonObjectPostDetails.getJSONObject("content").getString("rendered"),
                            jsonObjectPostDetails.getString("link")

                        )
                        postList.add(postDetails)
                    }

                    if (recyclerView is RecyclerView) {
                        with(recyclerView) {
                            layoutManager = LinearLayoutManager(context)
                            adapter = RecyclerViewPostAdapter(postList, 1)
                        }
                    }
                }, Response.ErrorListener {
                    println("API failed $it")
                }) {

                }
            queuePosts.add(requestForPosts)
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        category0=view.findViewById(R.id.category0)
        category1=view.findViewById(R.id.category1)

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