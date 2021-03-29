package site.yoonsang.instaclone.src.main.others.models

import com.google.gson.annotations.SerializedName

data class ResultFollowRequest(
    @SerializedName("followingId") val followingId: Int,
    @SerializedName("followerId") val followerId: Int,
    @SerializedName("status") val status: String
)