package site.yoonsang.instaclone.src.signup.phone.models

import com.google.gson.annotations.SerializedName

data class ResultPhoneSignUp(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userId") val userId: Int
)