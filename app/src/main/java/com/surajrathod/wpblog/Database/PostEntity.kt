package com.surajrathod.wpblog.Database

import androidx.room.Entity

@Entity(tableName = "PostTable")
data class PostEntity(

    var postId : Int,
    var postTitle : String,
    var postImg : String,
    var postDate : String,
    var postCategory : String,
    var postContent : String,
    var postUrl : String

)