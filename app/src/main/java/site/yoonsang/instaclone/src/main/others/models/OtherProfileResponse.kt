package site.yoonsang.instaclone.src.main.others.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class OtherProfileResponse(
    @SerializedName("result") val result: ResultOtherProfileRequest
):BaseResponse()