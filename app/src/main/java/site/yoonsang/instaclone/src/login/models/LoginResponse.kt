package site.yoonsang.instaclone.src.login.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class LoginResponse(
    @SerializedName("result") val result: ResultLoginRequest
):BaseResponse()