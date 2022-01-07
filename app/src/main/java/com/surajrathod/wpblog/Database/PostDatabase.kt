package com.surajrathod.wpblog.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun PostDao(): PostDao


    //using singleton pattern

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getDatabase(context: Context): PostDatabase {
            if (INSTANCE == null) {
                synchronized(this) {

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java,
                        "Post_db"
                    ).build()

                }
            }

            return INSTANCE!!
        }


    }


}