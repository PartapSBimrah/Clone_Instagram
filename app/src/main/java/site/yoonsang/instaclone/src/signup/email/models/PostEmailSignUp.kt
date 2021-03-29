package site.yoonsang.instaclone.src.signup.email.models

import com.google.gson.annotations.SerializedName

data class PostEmailSignUp(
    @SerializedName("name") val name: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email: String
)