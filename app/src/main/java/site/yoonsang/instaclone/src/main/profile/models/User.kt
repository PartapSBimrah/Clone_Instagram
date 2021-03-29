package site.yoonsang.instaclone.src.main.profile.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("profileImage") val profileImage: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("introduction") val introduction: String?
)