package com.surajrathod.wpblog.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
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


val postList = mutableListOf<PostDetails>()
val categoryList = mutableListOf<PostCategory>()
var dataLoaded = false

class DashboardFragment : Fragment() {

    lateinit var category0 : TextView
    lateinit var category1 : TextView
    lateinit var category2 : TextView
    lateinit var category3 : TextView

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
//        category0=view.findViewById(R.id.txtCategory0)
        category1=view.findViewById(R.id.txtCategory1)
        category2=view.findViewById(R.id.txtCategory2)
        category3=view.findViewById(R.id.txtCategory3)

        val binding = FragmentDashboardBinding.bind(view)
        val categories = arrayListOf<TextView>(binding.txtCategory0, category1, category2, category3)
        val recyclerView = view.findViewById<View>(R.id.postList)

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
                    categoryList.add(category)
                }


            },Response.ErrorListener {

            }){

            }
            queueCategories.add(requestForCategory)
            val requestForPosts =
                object : JsonArrayRequest(Request.Method.GET, urlPosts, null, Response.Listener {
                    println("Post API success $it")

                    /*if(postList.size!=it.length()) {*/
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
                            postList.add(postDetails)
                        }
                    binding.loadingCover.visibility=RelativeLayout.GONE
                    dataLoader(recyclerView as RecyclerView,categories)
                    dataLoaded=true

                }, Response.ErrorListener {
                    println("API failed $it")
                }) {

                }
            queuePosts.add(requestForPosts)

        }else if(dataLoaded){
            binding.loadingCover.visibility=RelativeLayout.GONE
            dataLoader(recyclerView as RecyclerView,categories)

        }else{
            binding.offlineCover.visibility=RelativeLayout.VISIBLE
            binding.btnGoOffline.setOnClickListener{
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToSavedPostFragment())
            }
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

     fun dataLoader(recyclerView: RecyclerView,categories : List<TextView>){
         for (i in 0 until categoryList.size){
             categories[i].text= categoryList[i].category
             categories[i].setOnClickListener {
                 findNavController().navigate(
                     DashboardFragmentDirections.actionDashboardFragmentToGenericPostsFragment(
                         categoryList[i].id
                     )
                 )
             }
         }
             with(recyclerView) {
                 layoutManager = LinearLayoutManager(context)
                 adapter = RecyclerViewPostAdapter(postList,0)
             }
     }


}