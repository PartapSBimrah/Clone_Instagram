package site.yoonsang.instaclone.src.main.home.feedMore.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class DeleteFeedResponse(
    @SerializedName("result") val result: String
):BaseResponse()