package site.yoonsang.instaclone.src.main.home.feedAdapter.models

import com.google.gson.annotations.SerializedName

data class FeedLikeRes(
    @SerializedName("feedId") val feedId: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("status") val status: String
)