package com.surajrathod.wpblog.model

data class PostDetails(
    val id : Int,
    val title : String,
    val img : String,
    val date : String,
    val category : String,
    val content : String,
    val url : String,
)