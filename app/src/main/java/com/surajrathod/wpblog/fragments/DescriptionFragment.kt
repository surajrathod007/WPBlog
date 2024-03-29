package com.surajrathod.wpblog.fragments

import android.os.Bundle


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.surajrathod.wpblog.Database.PostDatabase
import com.surajrathod.wpblog.Database.PostEntity


import com.surajrathod.wpblog.R
import com.surajrathod.wpblog.databinding.FragmentDescriptionBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DescriptionFragment : Fragment() {




    lateinit var db: PostDatabase
    val args by navArgs<DescriptionFragmentArgs>()
    lateinit var binding: FragmentDescriptionBinding


    lateinit var postTitle: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_description, container, false)

        binding = FragmentDescriptionBinding.bind(view)

        //get database

        db = PostDatabase.getDatabase(requireContext())


        //html content formating

        val content = args.post.content
        val pish = "<html>\n" +
                "<head>\n" +
                "\n" +
                "<style>\n" +
                "\n" +
                "body{\n" +
                "font-family: 'Open Sans', sans-serif;\n" +
                "color : #2b2839;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Open+Sans&display=swap\" rel=\"stylesheet\">\n" +
                "\n" +
                "</head>\n" +
                "<body>"
//            "<html>\n" +
//                    "<head>\n" +
//                    "<title>Page Title</title>\n" +
//                    "<style>\n" +
//                    "body {\n" +
//                    "  background-color: #ffffff;\n" +
//                    "  text-align: left;\n" +
//                    "  color: #2b2839;\n" +
//                    "  font-family: 'Oswald', sans-serif;\n" +
//                    "}\n" +
//
//                    "a { color : green;}" +
//                    "</style>\n" +
//                    "\n" +
//                    "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
//                    "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
//                    "<link href=\"https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap\" rel=\"stylesheet\">\n" +
//                    "</head>\n" +
//                    "<body>"
        val pas = "</body></html>"
        val myHtmlString = pish + content + pas

        //apply data to views
        binding.apply {

            txtPostTitle.text = args.post.title
            txtPostDate.text = args.post.date
            postWebView.loadDataWithBaseURL("", myHtmlString, "text/html", "UTF-8", "")

        }
        val category = fetchedCategoryList.find { args.post.category == it.id }
        if (category != null) {
            binding.txtPostCategory.text = category.category
        }
        Picasso.get().load(args.post.img).into(binding.postImg)


        binding.backBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(DescriptionFragmentDirections.actionDescriptionFragmentToDashboardFragment())
        }

        //Fav Button Checking

        GlobalScope.launch {
            val isFav = db.PostDao().isFav(args.post.id)
            if (isFav) {
                binding.btnFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.btnFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }


        //when fav button is clicked

        binding.btnFavourite.setOnClickListener {

            GlobalScope.launch {

                var isFav = db.PostDao().isFav(args.post.id) //check the post is in database or not

                if(isFav)
                {
                        db.PostDao().removeFav(args.post.id)
                        //Toast.makeText(requireContext(),"Removed From Favourites",Toast.LENGTH_SHORT).show()
                        binding.btnFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                }else{
                        db.PostDao().insert(

                            PostEntity(
                                args.post.id,
                                args.post.title,
                                args.post.img,
                                args.post.date,
                                args.post.category,
                                args.post.content,
                                args.post.url
                            )
                        )
                    binding.btnFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
                    //Toast.makeText(requireContext(), "Added To Favourites", Toast.LENGTH_SHORT).show()
                }
            }

        }


        //click on favourite


        return view
    }


    fun checkFav() {

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
//        val post by navArgs<DescriptionFragmentArgs>()
//        println(post)
    }


}