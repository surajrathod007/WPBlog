package com.surajrathod.wpblog.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PostDao {

    @Insert
    suspend fun insert(postEntity : PostEntity)

    @Delete
    suspend fun delete(postEntity: PostEntity)

    @Query("select * from PostTable order by postId desc")
    fun getAllPost() : LiveData<List<PostEntity>>


    @Query("select * from PostTable where postId = :postId1")
    fun getPostById(postId1 : Int) : PostEntity


    @Query("select exists(select * from PostTable where postId = :id)")
    fun isFav(id : Int): Boolean

    @Query("delete from PostTable where postId = :id")
    fun removeFav(id : Int)
}