package com.example.sessionapplication.data.reomte.model.news.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(
    tableName = "article"
)
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val author: String? ,
    val content: String? ,
    val description: String ?,
    val publishedAt: String?,
    val title: String?,
    val urlToImage: String?
) : Parcelable