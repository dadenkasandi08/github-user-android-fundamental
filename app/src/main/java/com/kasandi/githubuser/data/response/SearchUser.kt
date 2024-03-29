package com.kasandi.githubuser.data.response


import com.google.gson.annotations.SerializedName

data class SearchUser(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val userItems: List<UserItem>,
    @SerializedName("total_count")
    val totalCount: Int 
)