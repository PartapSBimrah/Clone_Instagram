package site.yoonsang.instaclone.src.main.profile.edit.models

import com.google.gson.annotations.SerializedName

data class ResultGetUserInfoRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("profileImage") val profileImage: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("introduce") val introduce: String?
)