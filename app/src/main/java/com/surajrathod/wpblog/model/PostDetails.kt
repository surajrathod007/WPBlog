package com.surajrathod.wpblog.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDetails(
    val id : Int,
    val title : String,
    val img : String,
    val date : String,
    val category : Int,
    val content : String,
    val url : String,
):Parcelable

