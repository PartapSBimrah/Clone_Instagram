package site.yoonsang.instaclone.src.main.profile.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class MyProfileResponse(
    @SerializedName("result") val result: ResultGetMyProfileRequest
): BaseResponse()