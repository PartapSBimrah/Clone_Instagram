package site.yoonsang.instaclone.src.main.profile.follow.follower.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyFollowerRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("profileImage") val profileImage: String
)