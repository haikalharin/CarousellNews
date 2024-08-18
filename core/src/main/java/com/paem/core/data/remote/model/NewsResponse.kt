package com.paem.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(

	@SerialName("description")
	val description: String? = null,

	@SerialName("time_created")
	val timeCreated: Int? = null,

	@SerialName("rank")
	val rank: Int? = null,

	@SerialName("banner_url")
	val bannerUrl: String? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("title")
	val title: String? = null
)
