package com.surajrathod.wpblog.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostTable")
data class PostEntity(

    @PrimaryKey
    var postId : Int,
    var postTitle : String,
    var postImg : String,
    var postDate : String,
    var postCategory : String,
    var postContent : String,
    var postUrl : String

)