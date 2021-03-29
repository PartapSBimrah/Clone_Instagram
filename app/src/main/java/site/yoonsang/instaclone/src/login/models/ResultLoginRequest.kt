package site.yoonsang.instaclone.src.login.models

import com.google.gson.annotations.SerializedName

data class ResultLoginRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("jwt") val jwt: String
)