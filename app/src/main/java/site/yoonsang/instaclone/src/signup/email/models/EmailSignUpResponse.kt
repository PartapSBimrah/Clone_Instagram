package site.yoonsang.instaclone.src.signup.email.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class EmailSignUpResponse(
    @SerializedName("result") val result: ResultEmailSignUp
): BaseResponse()