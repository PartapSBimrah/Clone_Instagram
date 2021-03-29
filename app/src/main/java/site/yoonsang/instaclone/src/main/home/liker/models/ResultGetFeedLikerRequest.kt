package site.yoonsang.instaclone.src.main.home.liker.models

import com.google.gson.annotations.SerializedName

data class ResultGetFeedLikerRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("profileImage") val profileImage: String?
)