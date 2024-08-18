package com.paem.core.utils

import com.paem.core.data.remote.model.NewsResponse
import com.paem.core.entities.News

fun NewsResponse.toNews(): News {
    return News(
        id = id ?: "",
        title = title ?: "",
        description = description ?: "",
        bannerUrl = bannerUrl ?: "",
        rank = rank?:0,
        timeCreated = timeCreated?:0

    )
}


