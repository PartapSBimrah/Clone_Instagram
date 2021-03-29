package site.yoonsang.instaclone.src.main.profile.edit.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class UserInfoResponse(
    @SerializedName("result") val result: ResultGetUserInfoRequest
): BaseResponse()