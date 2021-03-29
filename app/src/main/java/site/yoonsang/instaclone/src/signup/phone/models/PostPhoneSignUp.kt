package site.yoonsang.instaclone.src.signup.phone.models

import com.google.gson.annotations.SerializedName

data class PostPhoneSignUp(
    @SerializedName("name") val name: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
)