package com.paem.core.entities

import com.google.gson.annotations.SerializedName
import java.util.Date


data class News(
    val description: String,

    val timeCreated: Int,

    val rank: Int,

    val bannerUrl: String,

    val id: String,

    val title: String
)