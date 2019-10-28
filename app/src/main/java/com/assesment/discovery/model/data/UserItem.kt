package com.assesment.discovery.model.data

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserItem(
    @JsonProperty("id")
    var id: Int,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("username")
    var username: String,

    @JsonProperty("email")
    var email: String
)
