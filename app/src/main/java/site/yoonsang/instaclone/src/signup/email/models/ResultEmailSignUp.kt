package site.yoonsang.instaclone.src.signup.email.models

import com.google.gson.annotations.SerializedName

data class ResultEmailSignUp(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userId") val userId: Int
)