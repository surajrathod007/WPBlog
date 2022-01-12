package com.surajrathod.wpblog.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.adapters.CategoriesAdapter
import com.surajrathod.wpblog.adapters.RecyclerViewPostAdapter
import com.surajrathod.wpblog.databinding.FragmentDashboardBinding
import com.surajrathod.wpblog.internet.InternetStatus
import com.surajrathod.wpblog.model.PostCategory
import com.surajrathod.wpblog.model.PostDetails


val fetchedPostList = mutableListOf<PostDetails>()
val fetchedCategoryList = mutableListOf<PostCategory>()
var dataLoaded = false

class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding
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
        binding= FragmentDashboardBinding.bind(view)

        if(InternetStatus().checkForInternet(activity as Context) && !dataLoaded) {
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
                    fetchedCategoryList.add(category)
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
                                jsonObjectPostDetails.getJSONObject("content")
                                    .getString("rendered"),
                                jsonObjectPostDetails.getString("link")

                            )
                            fetchedPostList.add(postDetails)
                        }
                    binding.loadingCover.visibility=RelativeLayout.GONE
                    dataLoader()
                    dataLoaded=true

                }, Response.ErrorListener {
                    println("API failed $it")
                }) {

                }
            queuePosts.add(requestForPosts)

        }else if(dataLoaded){
            binding.loadingCover.visibility=RelativeLayout.GONE
            dataLoader()
        }else{
            binding.offlineCover.visibility=RelativeLayout.VISIBLE
            binding.btnGoOffline.setOnClickListener{
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToSavedPostFragment())
            }
        }
        return view
    }

     fun dataLoader(){
         binding.apply {
             with(binding){
                 postList.layoutManager = LinearLayoutManager(context)
                 postList.adapter = RecyclerViewPostAdapter(fetchedPostList,0)
                 categotyList.adapter= CategoriesAdapter(fetchedCategoryList)
                 categotyList.layoutManager= LinearLayoutManager(context,0,false)
             }
         }
     }

}