package com.assesment.discovery.model.data

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserPost(

    @JsonProperty("userId")
    val userId: Int? = null,

    @JsonProperty("id")
    val id: Int? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("body")
    val body: String? = null
)