package site.yoonsang.instaclone.src.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
    @SerializedName("phone_email_userName") val id: String,
    @SerializedName("password") val password: String
)