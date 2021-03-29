package site.yoonsang.instaclone.src.main.home.feedMore.modify.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class FeedModifyResponse(
    @SerializedName("result") val result: String
):BaseResponse()